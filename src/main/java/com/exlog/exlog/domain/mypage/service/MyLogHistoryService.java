package com.exlog.exlog.domain.mypage.service;

import com.exlog.exlog.domain.auth.entity.User;
import com.exlog.exlog.domain.auth.repository.UserRepository;
import com.exlog.exlog.domain.exercise.entity.ExerciseLog;
import com.exlog.exlog.domain.exercise.repository.ExerciseLogRepository;
import com.exlog.exlog.domain.mypage.dto.MyLogHistoryResDto;
import com.exlog.exlog.exception.CustomException;
import com.exlog.exlog.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyLogHistoryService {

    private final ExerciseLogRepository exerciseLogRepository;
    private final UserRepository userRepository;

    public Page<MyLogHistoryResDto> getAllHistory(Long userId, Pageable pageable) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        LocalDateTime startPoint = LocalDate.now().minusDays(45).atStartOfDay();

        Page<ExerciseLog> logPage = exerciseLogRepository.findAllByUserAndCreateAtAfterOrderByCreateAtDesc(user, startPoint, pageable);
        return logPage.map(MyLogHistoryResDto::fromEntity);
    }
}
