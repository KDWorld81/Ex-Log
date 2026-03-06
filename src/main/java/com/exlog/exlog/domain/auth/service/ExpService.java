package com.exlog.exlog.domain.auth.service;

import com.exlog.exlog.domain.auth.entity.User;
import com.exlog.exlog.domain.auth.repository.UserRepository;
import com.exlog.exlog.domain.exercise.repository.ExerciseLogRepository;
import com.exlog.exlog.exception.CustomException;
import com.exlog.exlog.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExpService {

    private final ExerciseLogRepository exerciseLogRepository;
    private final UserRepository userRepository;

    /**
     * 오늘 운동 횟수에 따른 경험치 지급
     * 당일 고유 운동 기록이 3개 이상일 경우 5 EXP를 부여
     *
     * @param userId 경험치를 업데이트할 사용자 ID
     * @throws CustomException 사용자를 찾을 수 없을 경우 발생
     */
    @Transactional
    public void updateExp(Long userId){

        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if(!user.canReceiveExp()){
            return;
        }

        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDate.now().atTime(LocalTime.MAX);
        Long todayExerciseCount = exerciseLogRepository.countDistinctExerciseByUserIdAndDate(user,start,end);

        if(todayExerciseCount >= 3){
            user.addExp(5);
        }
    }
}
