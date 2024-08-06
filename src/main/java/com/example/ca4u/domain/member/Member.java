package com.example.ca4u.domain.member;

import com.example.ca4u.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String socialId;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private String nickname;
    private String email;
    //프로필 사진
    private String imgUrl;


    // 기본값
    //@Enumerated(EnumType.STRING)
    //private Role role;
}
