package com.exlog.exlog.domain.title.controller;

import com.exlog.exlog.domain.title.dto.MainTitleReqDto;
import com.exlog.exlog.domain.title.service.MainTitleService;
import com.exlog.exlog.security.userdetail.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/maintitle")
public class MainTitleController {

    private final MainTitleService mainTitleService;

    @PostMapping
    public ResponseEntity<Void> selectMainTitle(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody MainTitleReqDto mainTitleReqDto) {
        System.out.println("컨트롤러에 도착한 titleId: " + mainTitleReqDto.getTitleId());
        mainTitleService.selectMainTitle(customUserDetails.getUserId(), mainTitleReqDto);
        return ResponseEntity.ok().build();
    }
}
