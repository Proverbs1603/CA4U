package com.example.ca4u.domain.guild.guildThumbnail;

import com.example.ca4u.domain.base.BaseEntity;
import com.example.ca4u.domain.guild.Guild;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class GuildThumbnail extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guild_thumbnail_id")
    private Long id;

    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guild_id")
    private Guild guild;
}
