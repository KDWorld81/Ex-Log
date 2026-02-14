package com.exlog.exlog.domain.exercise.service;

import com.exlog.exlog.domain.auth.entity.User;
import com.exlog.exlog.domain.auth.service.ExpService;
import com.exlog.exlog.domain.exercise.dto.ExerciseLogReqDto;
import com.exlog.exlog.domain.exercise.entity.Category;
import com.exlog.exlog.domain.exercise.entity.Exercise;
import com.exlog.exlog.domain.exercise.entity.ExerciseLog;
import com.exlog.exlog.domain.exercise.repository.ExerciseLogRepository;
import com.exlog.exlog.domain.exercise.repository.ExerciseRepository;
import com.exlog.exlog.domain.title.service.UserTitleService;
import com.exlog.exlog.exception.CustomException;
import com.exlog.exlog.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ExerciseLogService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseLogRepository exerciseLogRepository;
    private final UserTitleService userTitleService;
    private final ExpService expService;

    public Long exerciseLogging(User user, ExerciseLogReqDto exerciseLogReqDto) {
        // 해당 운동 종목이 존재하는가
        Exercise exercise = exerciseRepository.findById(exerciseLogReqDto.getExerciseId())
                .orElseThrow(() -> new CustomException(ErrorCode.EXERCISE_NOT_FOUND));

        if(exercise.getCategory().getCategoryId() == 6){
            if(exerciseLogReqDto.getMinutes() == null || exerciseLogReqDto.getMinutes() <= 0 ||
                    exerciseLogReqDto.getDistance() == null || exerciseLogReqDto.getDistance()<=0){
                throw new CustomException(ErrorCode.INVALID_INPUT_TIME);
            }
        }
        else{
            if(exerciseLogReqDto.getReps() == null || exerciseLogReqDto.getReps() <= 0 ||
                    exerciseLogReqDto.getSets() == null || exerciseLogReqDto.getSets() <= 0){
                throw new CustomException(ErrorCode.INVALID_INPUT_REPS_SETS);
            }
        }
        Category category = exercise.getCategory();
        // 존재했다면 엔티티로 생성
        ExerciseLog exerciseLog = exerciseLogReqDto.toEntity(user, exercise, category);
        // 저장후 생성된 ID 반환
        ExerciseLog savedLog = exerciseLogRepository.save(exerciseLog);

        expService.updateExp(user.getUserId());
        userTitleService.checkAndGrantTitles(user);
        return savedLog.getLogId();

    }
}
