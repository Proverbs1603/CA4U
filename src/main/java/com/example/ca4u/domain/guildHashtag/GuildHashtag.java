package com.example.ca4u.domain.guildHashtag;

import com.example.ca4u.domain.base.BaseEntity;
import com.example.ca4u.domain.category.Category;
import com.example.ca4u.domain.guild.Guild;
import com.example.ca4u.domain.hashtag.Hashtag;
import jakarta.persistence.*;

public class GuildHashtag extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guild_id")
    private Guild guild;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;
}
