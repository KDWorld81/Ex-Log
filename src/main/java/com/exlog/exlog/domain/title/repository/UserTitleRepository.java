package com.exlog.exlog.domain.title.repository;

import com.exlog.exlog.domain.auth.entity.User;
import com.exlog.exlog.domain.title.entity.Title;
import com.exlog.exlog.domain.title.entity.UserTitle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTitleRepository extends JpaRepository<UserTitle, Long> {
    List<UserTitle> findAllByUser_UserId(Long userId);

    boolean existsByUserAndTitle(User user, Title title);
}
