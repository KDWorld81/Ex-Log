package com.exlog.exlog.domain.title.repository;

import com.exlog.exlog.domain.title.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TitleRepository extends JpaRepository<Title, Long> {
    @Query("SELECT t FROM Title t WHERE t.titleId NOT IN " + "(SELECT ut.title.titleId FROM UserTitle ut WHERE ut.user.userId = :userId)")
    List<Title> findUnattainedTitlesByUserId(@Param("userId") Long userId); // 미획득 칭호

}
