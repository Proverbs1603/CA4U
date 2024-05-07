package com.example.ca4u.domain.category;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_nm", nullable = false)
    private String categoryNm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;
}
