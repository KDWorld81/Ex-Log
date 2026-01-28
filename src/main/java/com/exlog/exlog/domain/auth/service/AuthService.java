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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입을 처리하고 유저 정보를 저장
     * @param signupReqDto 가입 신청 데이터
     */
    @Transactional
    public void signUp(SignupReqDto signupReqDto) {
        validateDuplicateEmail(signupReqDto.getEmail());
        User user = signupReqDto.toEntity(passwordEncoder);
        userRepository.save(user);
    }

    /**
     * 로그인 인증 후 토큰 세트를 발급
     * @param loginReqDto 로그인 요청 데이터
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
     * 리프레시 토큰을 DB에 저장하거나 갱신
     * @param email 사용자 이메일
     * @param token 리프레시 토큰
     */
    private void saveRefreshToken(String email, String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByEmail(email)
                .map(entity -> entity.updateToken(token))
                .orElse(new RefreshToken(email, token));

        refreshTokenRepository.save(refreshToken);
    }

    /**
     * 이메일 중복 여부를 확인
     * @param email 검증할 이메일
     */
    private void validateDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }
    }

    /**
     * 비밀번호 일치 여부를 검증
     * @param rawPassword 입력된 비밀번호
     * @param encodedPassword 암호화된 비밀번호
     */
    private void validatePassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new CustomException(ErrorCode.PASSWORD_NOT_MATCH);
        }
    }
}