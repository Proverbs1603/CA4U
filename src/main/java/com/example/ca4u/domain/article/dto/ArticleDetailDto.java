package com.example.ca4u.domain.article.dto;

import com.example.ca4u.domain.article.Article;
import com.example.ca4u.domain.hashtag.HashtagDto;
import lombok.*;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailDto {
    private Long id;
    private String articleNm;
    private String articleDesc;
    private Boolean likes;
    private String imgUrl;
    private String department;
    private String actDay; //활동요일은 불러오기
    private Integer cost;
    private String actHour; //활동시간 불러오기

    private String roomLocation;  //동아리방 위치
    private String instaUrl;    //인스타 url

    private List<HashtagDto> hashtagDtoList;

    public static ArticleDetailDto of(Article article){
        return ArticleDetailDto.builder()
                .id(article.getId())
                .articleNm(article.getArticleNm())
                .articleDesc(article.getArticleDesc())
                .likes(article.getLikes())
                .imgUrl(article.getImgUrl())
                .department(article.getDepartment())
                .actDay(article.getActDay())
                .cost(article.getCost())
                .actHour(article.getActHour())
                .roomLocation(article.getRoomLocation())
                .instaUrl(article.getInstaUrl())
                .build();
    }

    public void setHashtagDtoList(List<HashtagDto> hashtagDtoList) {
        this.hashtagDtoList = hashtagDtoList;
    }
}
