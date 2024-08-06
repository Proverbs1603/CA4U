package com.example.ca4u.domain.guild;

import com.example.ca4u.domain.base.BaseEntity;
import com.example.ca4u.domain.category.Category;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity
public class Guild extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guild_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column
    private String recruit_desc;

    @Column
    private String guild_nm;

    @Column
    private String guild_brief_desc;

    @Column
    private String target_people_desc;

    @Column
    private String apply_desc;

    @Column
    private String act_day_desc;

    @Column
    private String location_desc;

    @ColumnDefault("0")
    @Column
    private Integer guild_num;

    @Column
    private String cost_desc;

    @Column
    private String spec_desc;

    @Column
    private String logo_img_url;
}
