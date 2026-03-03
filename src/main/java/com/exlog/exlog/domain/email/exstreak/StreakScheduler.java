package com.exlog.exlog.domain.email.exstreak;

import com.exlog.exlog.domain.auth.entity.User;
import com.exlog.exlog.domain.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StreakScheduler {

    private final UserRepository userRepository;
    private final ExerciseEmailService exerciseEmailService;

    @Scheduled(cron = "0 0 9 * * *") // 매일
    public void sendWarningEmails() {
        LocalDate threeDaysAgo = LocalDate.now().minusDays(3);
        List<User> targetUsers = userRepository.findUsersByLastExerciseDate(threeDaysAgo);

        targetUsers.forEach(exerciseEmailService::sendStreakWarningEmail);
    }
}
