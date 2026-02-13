package com.exlog.exlog.domain.title.controller;

import com.exlog.exlog.domain.title.dto.TitleResDto;
import com.exlog.exlog.domain.title.service.TitleService;
import com.exlog.exlog.security.userdetail.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/titles")
public class TitleController {

    private final TitleService titleService;

    @GetMapping
    public ResponseEntity<List<TitleResDto>> getAllTitles(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ResponseEntity.ok(titleService.findAllTitlesList(customUserDetails.getUserId()));
    }


}
