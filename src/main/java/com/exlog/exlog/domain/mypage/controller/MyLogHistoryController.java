package com.exlog.exlog.domain.mypage.controller;

import com.exlog.exlog.domain.mypage.dto.MyLogHistoryResDto;
import com.exlog.exlog.domain.mypage.service.MyLogHistoryService;
import com.exlog.exlog.security.userdetail.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/loghistory")
public class MyLogHistoryController {

    private final MyLogHistoryService myLogHistoryService;

    @GetMapping()
    public ResponseEntity<Page<MyLogHistoryResDto>> getMyHistory(@PageableDefault(size = 10, sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable,
                                                                 @AuthenticationPrincipal CustomUserDetails customUserDetails)
    {
        Page<MyLogHistoryResDto> responses = myLogHistoryService.getAllHistory(customUserDetails.getUser().getUserId(), pageable);
        return ResponseEntity.ok(responses);
    }
}
