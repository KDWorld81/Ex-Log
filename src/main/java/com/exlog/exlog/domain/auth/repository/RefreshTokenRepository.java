package com.exlog.exlog.domain.auth.repository;

import com.exlog.exlog.domain.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    Optional<RefreshToken> findByEmail(String email); // email로 리프레시 토큰 존재여부 확인

    void deleteByEmail(String email); // 로그아웃 시 토큰 삭제
}
