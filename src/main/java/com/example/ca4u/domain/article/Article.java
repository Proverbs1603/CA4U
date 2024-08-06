package com.example.ca4u.domain.article;

import com.example.ca4u.domain.base.BaseEntity;
import com.example.ca4u.domain.category.Category;
import com.example.ca4u.domain.guild.Guild;
import com.example.ca4u.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Article extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    //홍보글, 일반글
    @Column(name = "article_type", nullable = false)
    private String articleType;

    //게시글 제목
    @Column(name = "article_nm", nullable = false)
    private String articleNm;

    //게시글_글
    @Column(name = "article_desc")
    private String articleDesc;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "guild_id")
    private Guild guild;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
