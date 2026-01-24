package com.exlog.exlog.domain.exercise.repository;

import com.exlog.exlog.domain.exercise.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
