package com.fofo.core.member.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(uniqueConstraints = {@UniqueConstraint(name = "KAKAO_ID_UNIQUE", columnNames = {"KAKAO_ID"})})
public class Member extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private long id;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberImage> memberImageList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<HashTag> hashTags = new ArrayList<>();

    @OneToMany(mappedBy = "male", cascade = CascadeType.ALL)
    private List<MemberMatch> maleMemberMatchList = new ArrayList<>();

    @OneToMany(mappedBy = "female", cascade = CascadeType.ALL)
    private List<MemberMatch> femaleMemberMatchList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Address address;

    @NotNull @Size(max=20)
    private String name;

    @NotNull @Size(max=10)
    private String gender;

    @NotNull
    private LocalDateTime birth;

    @NotNull @Size(max=20)
    private String company;

    @NotNull @Size(max=20)
    private String job;

    @NotNull @Size(max=20)
    private String university;

    @NotNull @Size(max=4)
    private String mbti;

    @NotNull
    private Boolean smokingYn;

    @NotNull @Size(max=20)
    private String kakaoId;

    @NotNull @Size(max=10)
    private String religion;

    @Size(max=100)
    private String charmingPoint;

    @Size(max=100)
    private String filteringCondition;

    private Timestamp depositDate;

    @Size(max=100)
    private String note;

    @NotNull
    private int passCount;

    @NotNull
    private int chance;

    @NotNull
    @Size(max=10)
    private String approvalStatus;

    @NotNull @Size(max=1)
    private String status;

}
