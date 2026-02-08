package com.exlog.exlog.domain.exercise.repository;

import com.exlog.exlog.domain.auth.entity.User;
import com.exlog.exlog.domain.exercise.entity.ExerciseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, Long> {


    @Query("SELECT DISTINCT FUNCTION('DATE', el.createAt) " +
                  "FROM ExerciseLog el " +
                  "WHERE el.user = :user " +
                  "ORDER BY FUNCTION('DATE', el.createAt) ASC")
    List<java.sql.Date> findExerciseDatesByUser(@Param("user") User user);


    @Query("SELECT COUNT(DISTINCT el.exercise.exerciseId) " +
            "FROM ExerciseLog el " +
            "WHERE el.user = :user " +
            "AND el.createAt >= :start " +
            "AND el.createAt <= :end")
    Long countDistinctExerciseByUserIdAndDate(
            @Param("user") User user,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}
