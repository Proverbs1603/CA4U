package com.example.ca4u.domain.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("select a from Article a where a.likes = true")
    List<Article> findByLike();

    @Query("select a from Article a where lower(a.articleNm) like lower(concat('%', :keyword, '%')) or lower(a.articleDesc) like lower(concat('%', :keyword, '%'))")
    List<Article> findWithKeyword(@Param("keyword") String keyword);
}
