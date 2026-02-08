package com.exlog.exlog.domain.mypage.service;

import com.exlog.exlog.domain.auth.entity.Tier;
import com.exlog.exlog.domain.auth.entity.User;
import com.exlog.exlog.domain.exercise.repository.ExerciseLogRepository;
import com.exlog.exlog.domain.mypage.dto.MyPageResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final ExerciseLogRepository exerciseLogRepository;

    @Transactional(readOnly = true)
    public MyPageResDto getMyPage(User user) {
        List<LocalDate> grassData = exerciseLogRepository.findExerciseDatesByUser(user)
                .stream()
                .map(date -> date.toLocalDate())
                .toList();

        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDate.now().atTime(LocalTime.MAX);

        Long todayExerciseCount = exerciseLogRepository.countDistinctExerciseByUserIdAndDate(user,start,end);

        Tier currentTier = Tier.getTierByExp(user.getTotalExp());
        Long remainingExp = calculateRemaining(currentTier, user.getTotalExp());

        return MyPageResDto.fromEntity(user,remainingExp,todayExerciseCount,grassData);
    }

    private Long calculateRemaining(Tier currentTier, Long currentExp) {
        Tier nextTier = currentTier.getNextTier();
        if (nextTier == null) {return 0L;} // 챌린저는 남은 EXP 0
        return Math.max(0, nextTier.getMinExp() - currentExp);
    }
}