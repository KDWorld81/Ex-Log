package com.exlog.exlog.domain.auth.controller;

import com.exlog.exlog.domain.auth.dto.LoginReqDto;
import com.exlog.exlog.domain.auth.dto.LoginResDto;
import com.exlog.exlog.domain.auth.dto.SignupReqDto;
import com.exlog.exlog.domain.auth.service.AuthService;
import com.exlog.exlog.security.jwt.CookieUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 회원가입 API
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupReqDto signupReqDto) {
        authService.signUp(signupReqDto);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
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
}