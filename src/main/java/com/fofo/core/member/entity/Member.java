package com.fofo.core.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private long id;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberImage> memberImageList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<HashTag> hashTags = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Address address;

    private String name;

    private String gender;

    private Timestamp birth;

    private String company;

    private String job;

    private String university;

    private String mbti;

    private Boolean smokingYn;

    private String kakaoId;

    private String religion;

    private String charmingPoint;

    private String filteringCondition;

    private Timestamp depositDate;

    private String note;

    private int passCount;

    private int chance;

    private String approvalStatus;

    private String matchingStatus;

    private char stauts;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;
}
