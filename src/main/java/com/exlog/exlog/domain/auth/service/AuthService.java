package com.exlog.exlog.domain.auth.service;

import com.exlog.exlog.domain.auth.dto.LoginReqDto;
import com.exlog.exlog.domain.auth.dto.LoginResDto;
import com.exlog.exlog.domain.auth.dto.SignupReqDto;
import com.exlog.exlog.domain.auth.entity.RefreshToken;
import com.exlog.exlog.domain.auth.entity.Tier;
import com.exlog.exlog.domain.auth.entity.User;
import com.exlog.exlog.domain.auth.repository.RefreshTokenRepository;
import com.exlog.exlog.domain.auth.repository.UserRepository;
import com.exlog.exlog.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository; // 리프레시 토큰 저장용
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입 메서드
     */
    @Transactional
    public void signUp(SignupReqDto signupReqDto) {
        if (userRepository.existsByEmail(signupReqDto.getEmail())) {
            throw new RuntimeException("이미 존재하는 이메일입니다."); // TODO : GlobalException 전환 필요
        }

        // 비밀번호 암호화 및 유저 객체 생성
        User user = User.builder()
                .email(signupReqDto.getEmail())
                .password(passwordEncoder.encode(signupReqDto.getPassword()))
                .username(signupReqDto.getUsername())
                .totalExp(0L)
                .tier(Tier.BRONZE)
                .build();

        userRepository.save(user);
    }

    /**
     * 로그인 메서드
     */
    @Transactional
    public LoginResDto login(LoginReqDto loginReqDto) {

        // 1. 이메일로 유저 조회
        User user = userRepository.findByEmail(loginReqDto.getEmail())
                .orElseThrow(() -> new RuntimeException("가입되지 않은 이메일입니다."));

        // 2. 비밀번호 일치 여부 확인
        if (!passwordEncoder.matches(loginReqDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        // 3. JwtProvider를 사용하여 토큰 2종 발급
        String accessToken = jwtProvider.generateAccessToken(user.getEmail(), user.getUsername(), user.getUserId());
        String refreshToken = jwtProvider.generateRefreshToken(user.getEmail(), user.getUsername(), user.getUserId());

        // 4. 리프레시 토큰 DB 저장 (이미 있다면 업데이트)
        saveRefreshToken(user.getEmail(), refreshToken);

        return new LoginResDto(user.getUsername());
    }

    private void saveRefreshToken(String email, String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByEmail(email)
                .map(entity -> entity.updateToken(token))
                .orElse(new RefreshToken(email, token));

        refreshTokenRepository.save(refreshToken);
    }
}
