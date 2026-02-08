package com.exlog.exlog.domain.exercise.repository;

import com.exlog.exlog.domain.auth.entity.User;
import com.exlog.exlog.domain.exercise.entity.ExerciseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, Long> {

    @Query("SELECT DISTINCT CAST(el.createAt AS localdate) " + "FROM ExerciseLog el " + "WHERE el.user = :user " + "ORDER BY el.createAt ASC")
    List<LocalDate> findExerciseDatesByUser(@Param("user") User user); // 마이페이지 운동 잔디 조회

    @Query("SELECT COUNT(DISTINCT el.exercise.exerciseId) " + "FROM ExerciseLog el " + "WHERE el.user = :user " + "AND el.createAt >= :start " + "AND el.createAt <= :end")
    Long countDistinctExerciseByUserIdAndDate(@Param("user") User user, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end); // 당일 운동종목 수 계산
}
