package com.example.ca4u.domain.search;

import com.example.ca4u.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class SearchTag extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "search_tag_id")
    private Long id;

    private String tagNm;
}
