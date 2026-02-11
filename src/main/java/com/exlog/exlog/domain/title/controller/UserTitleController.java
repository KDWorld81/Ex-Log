package com.exlog.exlog.domain.title.controller;

import com.exlog.exlog.domain.title.dto.UserTitleResDto;
import com.exlog.exlog.domain.title.service.UserTitleService;
import com.exlog.exlog.security.userdetail.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/title")
@RequiredArgsConstructor
public class UserTitleController {

    private final UserTitleService userTitleService;

    // 사용자가 획득한 칭호 조회
    @GetMapping("/library")
    public ResponseEntity<List<UserTitleResDto>> getMyLibrary(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ResponseEntity.ok(userTitleService.getMyTitles(customUserDetails.getUserId()));
    }

}
