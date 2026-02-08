package com.exlog.exlog.domain.mypage.dto;

import com.exlog.exlog.domain.auth.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Builder
public class MyPageResDto {

        private final String username;
        private final String tier;
        private final Long totalExp;
        private final Long remainingExp; // 다음 단계까지 남은 XP
        private final String mainTitle;
        private final Long todayExerciseCount; // 현재 완료 종목 수 (2 / 3)
        private final List<LocalDate> grassData; // 운동한 날짜 목록 (잔디심기)

        public static MyPageResDto fromEntity(User user, Long remainingExp, Long todayExerciseCount, List<LocalDate> grassData) {
                return MyPageResDto.builder()
                        .username(user.getUsername())
                        .tier(user.getTier() != null ? user.getTier().name() : "BRONZE")
                        .totalExp(user.getTotalExp())
                        .remainingExp(remainingExp)
                        .mainTitle(user.getMainTitle() != null ? user.getMainTitle().getTitleName() : "칭호 없음")
                        .todayExerciseCount(todayExerciseCount)
                        .grassData(grassData)
                        .build();
        }
}
