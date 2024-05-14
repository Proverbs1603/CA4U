package com.example.ca4u.domain.articleHashtag;

import com.example.ca4u.domain.article.Article;
import com.example.ca4u.domain.hashtag.Hashtag;
import com.example.ca4u.domain.hashtag.HashtagDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleHashtagRepository extends JpaRepository<ArticleHashtag, Long> {

    //게시글 아이디로 해쉬태그 목록들 조회하기
    @Query("select h.id, h.tag from ArticleHashtag ah join Article A on ah.id = A.id join Hashtag h on ah.id = h.id where A.id = :articleId")
    List<HashtagDto> findByArticleId(@Param("articleId") Long articleId);

    //해쉬태그를 누르면 게시글이 조회되는 쿼리
    @Query("select a from Article a join ArticleHashtag ah ON a.id = ah.article.id where ah.hashtag.id = :hashtagId")
    List<Article> findArticlesByHashtagId(Long hashtagId);
}
