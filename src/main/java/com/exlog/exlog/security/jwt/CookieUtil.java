package com.exlog.exlog.security.jwt;

import org.springframework.http.ResponseCookie;

public class CookieUtil {
    public static ResponseCookie createCookie(String name, String value, long maxAge) {
        return ResponseCookie.from(name, value)
                .httpOnly(true)
                .secure(false) // 배포 시 true로 변경
                .path("/")
                .maxAge(maxAge)
                .sameSite("Lax")
                .build();
    }
}
