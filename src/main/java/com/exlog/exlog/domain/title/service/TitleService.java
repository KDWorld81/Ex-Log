package com.exlog.exlog.domain.title.service;

import com.exlog.exlog.domain.title.dto.TitleResDto;
import com.exlog.exlog.domain.title.entity.Title;
import com.exlog.exlog.domain.title.repository.TitleRepository;
import com.exlog.exlog.domain.title.repository.UserTitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TitleService {

    private final TitleRepository titleRepository;
    private final UserTitleRepository userTitleRepository; // 추가 필요

    public List<TitleResDto> findAllTitlesList(Long userId) {

        List<Title> allTitles = titleRepository.findAll();

        // 유저가 획득한 칭호 ID 목록
        List<Long> acquiredTitleIds = userTitleRepository.findAllByUser_UserId(userId)
                .stream()
                .map(ut -> ut.getTitle().getTitleId())
                .toList();

        return allTitles.stream()
                .map(title -> TitleResDto.fromEntity(title, acquiredTitleIds.contains(title.getTitleId())))
                .collect(Collectors.toList());
    }
}