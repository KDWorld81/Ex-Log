package com.exlog.exlog.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Builder

// 로그인 후 메인화면 보일때 필요한
public class LoginResDto {

    @JsonIgnore // 컨트롤러에서는 꺼낼수 있으나 JSON응답에는 포함되지 않도록
    private final String accessToken;

    @JsonIgnore
    private final String refreshToken;

    private final String username;

}
