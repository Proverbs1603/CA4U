package com.example.ca4u.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c " +
            "WHERE c.parent IS NULL")
    List<Category> findOnlyFstCategories();

    @Query("SELECT c FROM Category c WHERE c.parent.id IS NOT NULL AND c.parent.id = :fstCategoryId")
    List<Category> findAllSecCategoryByFstId(@Param("fstCategoryId") Long fstCategoryId);
}
