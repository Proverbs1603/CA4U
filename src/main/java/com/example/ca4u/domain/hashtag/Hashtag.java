package com.example.ca4u.domain.hashtag;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Hashtag{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id")
    private Long id;

    @Column(name = "tag", nullable = false)
    private String tag;
}