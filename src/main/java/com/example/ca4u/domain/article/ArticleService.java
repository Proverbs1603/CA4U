package com.example.ca4u.domain.article;

import com.example.ca4u.domain.article.dto.ArticleDto;
import com.example.ca4u.domain.article.dto.ArticleLikeDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ArticleService {
    private ArticleRepository articleRepository;

    @Transactional
    public void likeArticle(Long articleId) {
        //좋아요 +1
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new EntityNotFoundException("해당 게시글이 없습니다. id=" + articleId));
        article.likeArticle();
        articleRepository.save(article);
    }

    public List<ArticleLikeDto> getArticlesLike(){
        return articleRepository.findByLike().stream().map(ArticleLikeDto::of).toList();
    }

    public List<ArticleDto> getAllArticles(){
        return articleRepository.findAll().stream().map(ArticleDto::of).toList();
    }
}
