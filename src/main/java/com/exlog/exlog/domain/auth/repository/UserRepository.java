package com.exlog.exlog.domain.auth.repository;

import com.exlog.exlog.domain.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User>findByEmail(String email); // 조회

    Boolean existsByEmail(String email); // 존재 여부

    @Query("SELECT u FROM User u WHERE u.lastExerciseDate = :targetDate")
    List<User> findUsersByLastExerciseDate(@Param("targetDate") LocalDate targetDate); // 연속 3일 운동여부 체크

}
