package com.exlog.exlog.domain.mypage.controller;

import com.exlog.exlog.domain.mypage.dto.MyPageResDto;
import com.exlog.exlog.domain.mypage.service.MyPageService;
import com.exlog.exlog.security.userdetail.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mypage")
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping
    public ResponseEntity<MyPageResDto> getMyPage(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        MyPageResDto res = myPageService.getMyPage(customUserDetails.getUser());
        return ResponseEntity.ok(res);

    }
}
