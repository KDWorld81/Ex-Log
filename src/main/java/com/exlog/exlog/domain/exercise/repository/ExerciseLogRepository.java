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
    List<java.sql.Date> findExerciseDatesByUser(@Param("user") User user); // 잔디심기 mypage


    @Query("SELECT COUNT(DISTINCT el.exercise.exerciseId) " + "FROM ExerciseLog el " + "WHERE el.user = :user " + "AND el.createAt >= :start " + "AND el.createAt <= :end")
    Long countDistinctExerciseByUserIdAndDate(@Param("user") User user, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end); // 당일 운동 기록 시 mypage에 count (같은 exercise는 기록 X -> 조작 방지)

    @Query("SELECT COUNT(e) FROM ExerciseLog e WHERE e.user.userId = :userId AND e.category = :category")
    long countByUserIdAndCategory(@Param("userId") Long userId, @Param("category") Category category); // 칭호를 위한 누적 통계

    @Query("SELECT el.createAt FROM ExerciseLog el " + "WHERE el.user.userId = :userId AND el.category.categoryId = :categoryId")
    List<LocalDateTime> findAllCreateAtByUserIdAndCategoryId(@Param("userId") Long userId, @Param("categoryId") Long categoryId); // 운동 일시 조회 (빈도수)

    @Query("SELECT DISTINCT CAST(e.createAt AS date) FROM ExerciseLog e " + "WHERE e.user.userId = :userId ORDER BY CAST(e.createAt AS date) DESC")
    List<LocalDate> findDistinctWorkoutDates(@Param("userId") Long userId); // 운동을 몇일간 연속으로 했는지 조회


}
