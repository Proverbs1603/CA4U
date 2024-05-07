package com.example.ca4u.domain.article.dto;

import com.example.ca4u.domain.article.Article;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    private String articleNm;
    private String articleDesc;
    private Boolean likes;
    private String imgUrl;
    private String department;
    private String actDay; //활동요일은 불러오기
    private Integer cost;
    private String actHour; //활동시간 불러오기


    public static ArticleDto of(Article article){
        return ArticleDto.builder()
                .id(article.getId())
                .articleNm(article.getArticleNm())
                .articleDesc(article.getArticleDesc())
                .likes(article.getLikes())
                .imgUrl(article.getImgUrl())
                .department(article.getDepartment())
                .actDay(article.getActDay())
                .cost(article.getCost())
                .actHour(article.getActHour())
                .build();
    }
}
