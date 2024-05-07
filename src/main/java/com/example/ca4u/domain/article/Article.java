package com.example.ca4u.domain.article;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Article {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    //학회 또는 동아리
    @Column(name = "type", nullable = false)
    private String articleType;

    //동아리 or 학회 이름
    @Column(name = "article_nm", nullable = false)
    private String articleNm;

    //동아리 or 학회 간략설명
    @Column(name = "article_desc")
    private String articleDesc;

    //좋아요 , 즐겨찾기
    @Column(name = "likes", nullable = false)
    private Boolean likes;

    //동아리방 위치
    @Column(name = "room_location")
    private String roomLocation;

    //회비
    @Column(name = "cost")
    private Integer cost;

    //관심분과
    @Column(name = "department")
    private String department;

    //활동시간
    @Column(name = "act_hour")
    private String actHour;

    //활동시간
    @Column(name = "act_day")
    private String actDay;

    //인스타 url
    @Column(name = "insta_url")
    private String instaUrl;

    //게시글 이미지 url
    @Column(name = "img_url")
    private String imgUrl;

    public void likeArticle(){
        this.likes = !this.likes;
    }
}
