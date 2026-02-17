package com.exlog.exlog.domain.title.service;

import com.exlog.exlog.domain.auth.entity.User;
import com.exlog.exlog.domain.auth.repository.UserRepository;
import com.exlog.exlog.domain.title.dto.MainTitleReqDto;
import com.exlog.exlog.domain.title.entity.Title;
import com.exlog.exlog.domain.title.repository.TitleRepository;
import com.exlog.exlog.domain.title.repository.UserTitleRepository;
import com.exlog.exlog.exception.CustomException;
import com.exlog.exlog.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MainTitleService {

    private final UserRepository userRepository;
    private final TitleRepository titleRepository;
    private final UserTitleRepository userTitleRepository;

    @Transactional
    public void selectMainTitle(Long userId, MainTitleReqDto mainTitleReqDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Title title = titleRepository.findById(mainTitleReqDto.getTitleId()).orElseThrow(() -> new CustomException(ErrorCode.TITLE_NOT_FOUND));

        boolean isOwned = userTitleRepository.existsByUserAndTitle(user, title);
        if (!isOwned) {
            throw new CustomException(ErrorCode.TITLE_NOT_OWNED);
        }

        user.updateMainTitle(title);
    }
}
