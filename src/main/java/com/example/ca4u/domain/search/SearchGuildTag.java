package com.example.ca4u.domain.search;

import com.example.ca4u.domain.base.BaseEntity;
import com.example.ca4u.domain.guild.Guild;
import com.example.ca4u.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class SearchGuildTag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "search_guild_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "search_tag_id")
    private SearchTag searchTag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guild_id")
    private Guild guild;
}
