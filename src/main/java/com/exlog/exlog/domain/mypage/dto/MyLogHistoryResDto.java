package com.exlog.exlog.domain.mypage.dto;

import com.exlog.exlog.domain.exercise.entity.ExerciseLog;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@RequiredArgsConstructor
public class MyLogHistoryResDto {

    private final Long logId;
    private final Long exerciseId;
    private final String exerciseName;
    private final Long reps;
    private final Long sets;
    private final Integer minutes;
    private final Double distance;
    private final LocalDateTime createAt;

    public static MyLogHistoryResDto fromEntity(ExerciseLog exerciseLog){
        return MyLogHistoryResDto.builder()
                .logId(exerciseLog.getLogId())
                .exerciseId(exerciseLog.getExercise().getExerciseId())
                .exerciseName(exerciseLog.getExercise().getExerciseName())
                .reps(exerciseLog.getReps())
                .sets(exerciseLog.getSets())
                .minutes(exerciseLog.getMinutes())
                .distance(exerciseLog.getDistance())
                .createAt(exerciseLog.getCreateAt())
                .build();
    }
}
