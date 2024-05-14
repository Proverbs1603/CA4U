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

    @Query("select a from Article a where a.id in (1,2,3,4,5)")
    List<Article> findPersonalArticles_1();

    @Query("select a from Article a where a.id in (6,7,8,9,10)")
    List<Article> findPersonalArticles_2();

    @Query("select a from Article a where a.id in (11,12,13,14,15)")
    List<Article> findPersonalArticles_3();
}
