package com.exlog.exlog.domain.mypage.service;

import com.exlog.exlog.domain.auth.entity.Tier;
import com.exlog.exlog.domain.auth.entity.User;
import com.exlog.exlog.domain.auth.repository.UserRepository;
import com.exlog.exlog.domain.exercise.repository.ExerciseLogRepository;
import com.exlog.exlog.domain.mypage.dto.MyPageResDto;
import com.exlog.exlog.exception.CustomException;
import com.exlog.exlog.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final ExerciseLogRepository exerciseLogRepository;
    private final UserRepository userRepository;

    /**
     * 마이페이지 메인 정보 조회
     * 사용자 정보, 운동 잔디 데이터, 오늘 운동 횟수, 다음 티어까지 남은 경험치를 계산하여 반환
     *
     * @param userId 조회 대상 사용자 ID
     * @return 마이페이지 응답 DTO (티어, 경험치, 잔디, 오늘 운동 횟수 등)
     * @throws CustomException 사용자를 찾을 수 없을 경우 발생 (USER_NOT_FOUND)
     */
    @Transactional(readOnly = true)
    public MyPageResDto getMyPage(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        List<LocalDate> grassData = exerciseLogRepository.findExerciseDatesByUser(user)
                .stream()
                .map(Date::toLocalDate)
                .toList();

        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDate.now().atTime(LocalTime.MAX);

        Long todayExerciseCount = exerciseLogRepository.countDistinctExerciseByUserIdAndDate(user,start,end);

        Tier currentTier = Tier.getTierByExp(user.getTotalExp());
        Long remainingExp = calculateRemaining(currentTier, user.getTotalExp());

        return MyPageResDto.fromEntity(user,remainingExp,todayExerciseCount,grassData);
    }

    /**
     * 다음 티어 승급까지 남은 경험치 계산
     *
     * @param currentTier 현재 사용자 티어
     * @param currentExp 현재 누적 경험치
     * @return 다음 티어까지 필요한 남은 경험치 (최고 티어일 경우 0)
     */
    private Long calculateRemaining(Tier currentTier, Long currentExp) {
        Tier nextTier = currentTier.getNextTier();
        if (nextTier == null) {return 0L;} // 챌린저는 남은 EXP 0
        return Math.max(0, nextTier.getMinExp() - currentExp);
    }
}