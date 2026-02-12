package com.exlog.exlog.domain.exercise.repository;

import com.exlog.exlog.domain.auth.entity.User;
import com.exlog.exlog.domain.exercise.entity.Category;
import com.exlog.exlog.domain.exercise.entity.ExerciseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, Long> {


    @Query("SELECT DISTINCT FUNCTION('DATE', el.createAt) " + "FROM ExerciseLog el " + "WHERE el.user = :user " + "ORDER BY FUNCTION('DATE', el.createAt) ASC")
    List<java.sql.Date> findExerciseDatesByUser(@Param("user") User user);


    @Query("SELECT COUNT(DISTINCT el.exercise.exerciseId) " + "FROM ExerciseLog el " + "WHERE el.user = :user " + "AND el.createAt >= :start " + "AND el.createAt <= :end")
    Long countDistinctExerciseByUserIdAndDate(@Param("user") User user, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT COUNT(e) FROM ExerciseLog e WHERE e.user.userId = :userId AND e.category = :category")
    long countByUserIdAndCategory(@Param("userId") Long userId, @Param("category") Category category);

    @Query("SELECT el.createAt FROM ExerciseLog el " + "WHERE el.user.userId = :userId AND el.category.categoryId = :categoryId")
    List<LocalDateTime> findAllCreateAtByUserIdAndCategoryId(@Param("userId") Long userId, @Param("categoryId") Long categoryId);

    @Query("SELECT DISTINCT CAST(e.createAt AS date) FROM ExerciseLog e " + "WHERE e.user.userId = :userId ORDER BY CAST(e.createAt AS date) DESC")
    List<LocalDate> findDistinctWorkoutDates(@Param("userId") Long userId);


}
