package com.exlog.exlog.domain.auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)

public class LoginReqDto {
    private final String email;
    private final String password;
}
