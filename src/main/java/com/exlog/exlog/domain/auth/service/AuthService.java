package com.exlog.exlog.domain.auth.service;

import com.exlog.exlog.domain.auth.dto.LoginReqDto;
import com.exlog.exlog.domain.auth.dto.LoginResDto;
import com.exlog.exlog.domain.auth.dto.SignupReqDto;
import com.exlog.exlog.domain.auth.entity.RefreshToken;
import com.exlog.exlog.domain.auth.entity.User;
import com.exlog.exlog.domain.auth.repository.RefreshTokenRepository;
import com.exlog.exlog.domain.auth.repository.UserRepository;
import com.exlog.exlog.exception.CustomException;
import com.exlog.exlog.exception.ErrorCode;
import com.exlog.exlog.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final StringRedisTemplate redisTemplate;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    /**
     * 새로운 사용자를 등록
     * 이메일 중복 검사와 이메일 인증 완료 여부를 사전에 검증
     *
     * @param signupReqDto 회원가입 요청 정보 (이메일, 비밀번호, 닉네임 등)
     * @throws CustomException 이메일이 중복되거나 인증되지 않았을 경우 발생
     */
    @Transactional
    public void signUp(SignupReqDto signupReqDto) {
        validateDuplicateEmail(signupReqDto.getEmail());
        validateEmailVerification(signupReqDto.getEmail());

        User user = signupReqDto.toEntity(passwordEncoder);
        userRepository.save(user);

        // 가입 완료 후 Redis의 인증 티켓을 즉시 삭제
        redisTemplate.delete("VERIFIED_EMAIL:" + signupReqDto.getEmail());
    }

    /**
     * 로그인 인증 후 Access Token과 Refresh Token 세트를 발급
     *
     * @param loginReqDto 로그인 요청 데이터 (이메일, 비밀번호)
     * @return 발급된 토큰 정보와 사용자 이름을 포함한 응답 객체
     * @throws CustomException 사용자를 찾을 수 없거나 비밀번호가 일치하지 않을 경우 발생
     */
    @Transactional
    public LoginResDto login(LoginReqDto loginReqDto) {
        User user = userRepository.findByEmail(loginReqDto.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        validatePassword(loginReqDto.getPassword(), user.getPassword());

        String accessToken = jwtProvider.generateAccessToken(user.getEmail(), user.getUsername(), user.getUserId());
        String refreshToken = jwtProvider.generateRefreshToken(user.getEmail(), user.getUsername(), user.getUserId());

        saveRefreshToken(user.getEmail(), refreshToken);
        return LoginResDto.from(accessToken, refreshToken, user.getUsername());
    }

    /**
     * 리프레시 토큰을 DB에 저장하거나 기존 토큰을 갱신
     *
     * @param email 사용자 이메일
     * @param token 새로 발급된 리프레시 토큰
     */
    private void saveRefreshToken(String email, String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByEmail(email)
                .map(entity -> entity.updateToken(token))
                .orElse(new RefreshToken(email, token));

        refreshTokenRepository.save(refreshToken);
    }

    /**
     * 시스템에 이미 등록된 이메일인지 확인
     *
     * @param email 검증할 이메일 주소
     * @throws CustomException 이미 가입된 이메일인 경우 발생
     */
    private void validateDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }
    }

    /**
     * Redis를 조회하여 해당 이메일의 인증 완료 여부를 검증
     * 인증 성공 시 생성된 특정 플래그(DONE)의 존재를 확인
     *
     * @param email 검증할 이메일 주소
     * @throws CustomException 이메일 인증 정보가 없거나 만료된 경우 발생
     */
    private void validateEmailVerification(String email) {
        String isVerified = redisTemplate.opsForValue().get("VERIFIED_EMAIL:" + email);
        if (!"DONE".equals(isVerified)) {
            throw new CustomException(ErrorCode.EMAIL_NOT_VERIFIED);
        }
    }

    /**
     * 입력된 평문 비밀번호와 암호화된 비밀번호의 일치 여부를 검증
     *
     * @param rawPassword     사용자가 입력한 비밀번호
     * @param encodedPassword DB에 저장된 암호화된 비밀번호
     * @throws CustomException 비밀번호가 일치하지 않을 경우 발생
     */
    private void validatePassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new CustomException(ErrorCode.PASSWORD_NOT_MATCH);
        }
    }
}