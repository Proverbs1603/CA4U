package com.example.ca4u.domain.guild.albumPhoto;

import com.example.ca4u.domain.base.BaseEntity;
import com.example.ca4u.domain.guild.album.Album;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class AlbumPhoto extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_photo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    private String photoUrl;
}
