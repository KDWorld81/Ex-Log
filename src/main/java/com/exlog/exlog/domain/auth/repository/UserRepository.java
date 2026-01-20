package com.exlog.exlog.domain.auth.repository;

import com.exlog.exlog.domain.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User>findByEmail(String email); // 조회

    Boolean existsByEmail(String email); // 존재 여부

}
