package com.example.ca4u.domain.article.dto;

import com.example.ca4u.domain.article.Article;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ArticleLikeDto {
    private Long id;
    private String articleNm;
    private String articleDesc;

    public static ArticleLikeDto of(Article article){
        return ArticleLikeDto.builder()
                .id(article.getId())
                .articleNm(article.getArticleNm())
                .articleDesc(article.getArticleDesc())
                .build();
    }
}
