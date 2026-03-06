package com.exlog.exlog.domain.exercise.service;

import com.exlog.exlog.domain.auth.entity.User;
import com.exlog.exlog.domain.auth.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final UserTitleService userTitleService;
    private final ExpService expService;

    /**
     * 사용자의 운동 기록을 저장하고 관련 보상(EXP, 칭호)을 처리합니다.
     * 카테고리(유산소/무산소)에 따라 입력값(시간/거리 또는 횟수/세트)의 유효성을 검증
     *
     * @param userId 운동을 기록하는 사용자 ID
     * @param exerciseLogReqDto 운동 상세 정보 (종목 ID, 횟수, 세트, 시간, 거리 등)
     * @return 생성된 운동 기록의 식별자(ID)
     * @throws CustomException 사용자/종목 미존재 시, 또는 카테고리별 필수 입력값이 누락되었을 경우 발생
     */

    public Long exerciseLogging(Long userId, ExerciseLogReqDto exerciseLogReqDto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

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
        user.updateLastExerciseDate();

        return savedLog.getLogId();

    }
}
