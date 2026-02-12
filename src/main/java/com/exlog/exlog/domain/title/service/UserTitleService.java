package com.exlog.exlog.domain.title.service;

import com.exlog.exlog.domain.auth.entity.User;
import com.exlog.exlog.domain.exercise.repository.ExerciseLogRepository;
import com.exlog.exlog.domain.title.dto.UserTitleResDto;
import com.exlog.exlog.domain.title.entity.Title;
import com.exlog.exlog.domain.title.entity.UserTitle;
import com.exlog.exlog.domain.title.repository.TitleRepository;
import com.exlog.exlog.domain.title.repository.UserTitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserTitleService {

    private final TitleRepository titleRepository;
    private final UserTitleRepository userTitleRepository;
    private final ExerciseLogRepository exerciseLogRepository;

    /**
     *  칭호 획득 체크 로직
     */
    @Transactional
    public void checkAndGrantTitles(User user) {
        List<Title> lockedTitles = titleRepository.findUnattainedTitlesByUserId(user.getUserId());

        for (Title title : lockedTitles) {
            long currentProgress = calculateProgress(user, title);

            if (currentProgress >= title.getThreshold()) {
                grantTitle(user, title);
            }
        }
    }

    /**
     *  나의 칭호 조회 로직
     */
    public List<UserTitleResDto> getMyTitles(Long userId) {
        return userTitleRepository.findAllByUser_UserId(userId).stream()
                .map(UserTitleResDto::fromEntity)
                .toList();
    }

    private long calculateProgress(User user, Title title) {
        return switch (title.getTitleCondition()) {

            case CATEGORY_COUNT ->
                    exerciseLogRepository.countByUserIdAndCategory(user.getUserId(), title.getCategory());

            case WEEKLY_FREQUENCY -> {
                LocalDateTime weekAgo = LocalDateTime.now().minusDays(7);
                List<LocalDateTime> logs = exerciseLogRepository.findAllCreateAtByUserIdAndCategoryId(
                        user.getUserId(), title.getCategory().getCategoryId());

                yield logs.stream()
                        .filter(dt -> dt.isAfter(weekAgo))
                        .map(LocalDateTime::toLocalDate)
                        .distinct()
                        .count();
            }

            case STREAK -> calculateCurrentStreak(user);
        };
    }

    private long calculateCurrentStreak(User user) {
        // findExerciseDatesByUser는 List<java.sql.Date>를 반환하므로 변환 필요
        List<java.sql.Date> sqlDates = exerciseLogRepository.findExerciseDatesByUser(user);

        List<LocalDate> dates = sqlDates.stream()
                .map(java.sql.Date::toLocalDate)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .toList();

        if (dates.isEmpty()) return 0;

        long streak = 1;
        LocalDate today = LocalDate.now();
        if (!dates.get(0).equals(today) && !dates.get(0).equals(today.minusDays(1))) return 0;

        for (int i = 0; i < dates.size() - 1; i++) {
            if (dates.get(i).minusDays(1).equals(dates.get(i + 1))) streak++;
            else break;
        }
        return streak;
    }

    private void grantTitle(User user, Title title) {
        userTitleRepository.save(UserTitle.builder().user(user).title(title).build());
    }

}