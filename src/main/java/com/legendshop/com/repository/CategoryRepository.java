package com.legendshop.com.repository;

import com.legendshop.com.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Category entity.
 */
@SuppressWarnings("unused")
public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query("select c from Category c where c.parentCategory.id = :categoryId")
    List<Category> getAllSubCategories(@Param("categoryId") Long categoryId);

    @Query("select c from Category c where c.parentCategory.id is null")
    List<Category> getAllFirstLevelCategories();
}
