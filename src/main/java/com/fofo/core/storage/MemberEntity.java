package com.fofo.core.storage;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBER", uniqueConstraints = {@UniqueConstraint(name = "KAKAO_ID_UNIQUE", columnNames = {"KAKAO_ID"})})
public class MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @NotNull
    @Size(max = 20)
    private String kakaoId;

    @NotNull
    private Long addressId;

    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    private String gender;

    @NotNull
    private LocalDateTime birthday;

    @NotNull
    private Integer age;

    @Size(max = 10)
    private String filteringConditionAgeRelation;

    @NotNull
    @Size(max = 20)
    private String company;

    @NotNull
    @Size(max = 20)
    private String job;

    @NotNull
    @Size(max = 20)
    private String university;

    @NotNull
    private String mbti;

    @NotNull
    private Boolean smokingYn;

    private Boolean filteringConditionSmokingYn;

    @NotNull
    @Size(max = 20)
    private String religion;

    @Size(max = 20)
    private String filteringConditionReligion;

    @Size(max = 100)
    private String charmingPoint;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime depositDate;

    @Size(max = 100)
    private String note;

    @ColumnDefault("5")
    private Integer passCount;

    @ColumnDefault("2")
    private Integer chance;

    @NotNull
    @Size(max = 20)
    private String approvalStatus;

    @NotNull
    @Size(max = 1)
    private String status;

    private MemberEntity(final String kakaoId,
                         final Long addressId,
                         final String name,
                         final String gender,
                         final LocalDateTime birthday,
                         final Integer age,
                         final String filteringConditionAgeRelation,
                         final String company,
                         final String job,
                         final String university,
                         final String mbti,
                         final Boolean smokingYn,
                         final Boolean filteringConditionSmokingYn,
                         final String religion,
                         final String filteringConditionReligion,
                         final String charmingPoint,
                         final LocalDateTime depositDate,
                         final String note,
                         final Integer passCount,
                         final Integer chance,
                         final String approvalStatus,
                         final String status
    ) {
        this.kakaoId = kakaoId;
        this.addressId = addressId;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.age = age;
        this.filteringConditionAgeRelation = filteringConditionAgeRelation;
        this.company = company;
        this.job = job;
        this.university = university;
        this.mbti = mbti;
        this.smokingYn = smokingYn;
        this.filteringConditionSmokingYn = filteringConditionSmokingYn;
        this.religion = religion;
        this.filteringConditionReligion = filteringConditionReligion;
        this.charmingPoint = charmingPoint;
        this.depositDate = depositDate;
        this.note = note;
        this.passCount = passCount;
        this.chance = chance;
        this.approvalStatus = approvalStatus;
        this.status = status;
    }

    public static MemberEntity of(
            final String kakaoId,
            final Long addressId,
            final String name,
            final String gender,
            final LocalDateTime birthday,
            final Integer age,
            final String filteringConditionAgeRelation,
            final String company,
            final String job,
            final String university,
            final String mbti,
            final Boolean smokingYn,
            final Boolean filteringConditionSmokingYn,
            final String religion,
            final String filteringConditionReligion,
            final String charmingPoint,
            final LocalDateTime depositDate,
            final String note,
            final Integer passCount,
            final Integer chance,
            final String approvalStatus,
            final String status
    ) {
        return new MemberEntity(kakaoId,
                addressId,
                name,
                gender,
                birthday,
                age,
                filteringConditionAgeRelation,
                company,
                job,
                university,
                mbti,
                smokingYn,
                filteringConditionSmokingYn,
                religion,
                filteringConditionReligion,
                charmingPoint,
                depositDate,
                note,
                passCount,
                chance,
                approvalStatus,
                status);
    }


}
