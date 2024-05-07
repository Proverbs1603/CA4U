package com.example.ca4u.domain.articleHashtag;

import com.example.ca4u.domain.article.Article;
import com.example.ca4u.domain.hashtag.Hashtag;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class ArticleHashtag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_hashtag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;
}