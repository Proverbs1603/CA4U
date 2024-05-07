package com.example.ca4u.domain.article;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity //동아리or학회 신청방법
public class ArticleApply {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_apply_id")
    private Long id;

    //신청방법 내용
    @Column(name = "apply_nm", nullable = false)
    private String applyNm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;
}
