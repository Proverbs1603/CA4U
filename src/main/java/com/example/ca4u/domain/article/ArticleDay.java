package com.example.ca4u.domain.article;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity //동아리or학회 활동요일
public class ArticleDay {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_day_id")
    private Long id;

    //신청방법 내용
    @Column(name = "day", nullable = false)
    private String day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;
}
