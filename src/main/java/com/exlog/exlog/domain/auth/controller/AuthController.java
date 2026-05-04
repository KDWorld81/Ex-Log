package com.exlog.exlog.domain.auth.controller;

import com.exlog.exlog.domain.auth.dto.LoginReqDto;
import com.exlog.exlog.domain.auth.dto.LoginResDto;
import com.exlog.exlog.domain.auth.dto.SignupReqDto;
import com.exlog.exlog.domain.auth.service.AuthService;
import com.exlog.exlog.domain.email.SignUpEmailService;
import com.exlog.exlog.security.jwt.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final SignUpEmailService signUpEmailService;

    // 회원가입 API
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupReqDto signupReqDto) {
        authService.signUp(signupReqDto);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    // 인증번호 발송
    @PostMapping("/send")
    public ResponseEntity<String> sendCode(@RequestParam String email) {
        signUpEmailService.sendVerificationCode(email);
        return ResponseEntity.ok("인증 번호가 발송되었습니다.");
    }

    // 인증번호 검증
    @PostMapping("/verify")
    public ResponseEntity<String> verifyCode(@RequestParam String email, @RequestParam String code) {
        boolean isVerified = signUpEmailService.verifyCode(email, code);
        if (isVerified) {
            return ResponseEntity.ok("인증 성공!");
        }
        return ResponseEntity.badRequest().body("인증 번호가 틀렸거나 만료되었습니다.");
    }

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<LoginResDto> login(@RequestBody LoginReqDto loginReqDto) {
        // 토큰과 유저 정보 가져오기
        LoginResDto loginRes = authService.login(loginReqDto);

        ResponseCookie accessCookie = CookieUtil.createCookie("accessToken", loginRes.getAccessToken(), 1800);
        ResponseCookie refreshCookie = CookieUtil.createCookie("refreshToken", loginRes.getRefreshToken(), 1209600);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, accessCookie.toString())
                .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                .body(loginRes);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        // 1. 요청의 쿠키에서 토큰 꺼내기
        String token = null;
        if (request.getCookies() != null) {
            token = Arrays.stream(request.getCookies())
                    .filter(c -> "accessToken".equals(c.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
        }

        if (token != null) {
            // 2. 서비스 로직 실행 (DB 리프레시 토큰 삭제 + 레디스 블랙리스트 등록)
            authService.logout(token);

            // 3. 클라이언트 쿠키 삭제 (Max-Age를 0으로 설정)
            Cookie cookie = new Cookie("accessToken", null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            cookie.setHttpOnly(true); // 보안 설정 유지
            response.addCookie(cookie);
        }

        return ResponseEntity.ok("성공적으로 로그아웃되었습니다.");
    }
}