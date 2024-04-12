package com.fofo.core.storage;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.member.AgeRelationType;
import com.fofo.core.domain.member.ApprovalStatus;
import com.fofo.core.domain.member.Gender;
import com.fofo.core.domain.member.Mbti;
import com.fofo.core.domain.member.Religion;
import com.fofo.core.storage.converter.ApprovalStatusConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBER", uniqueConstraints = {@UniqueConstraint(name = "KAKAO_ID_UNIQUE", columnNames = {"KAKAO_ID"})})
public class MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String kakaoId;

    @Column(nullable = false)
    private Long addressId;

    @Column(nullable = false, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private LocalDateTime birthday;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, length = 30)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private AgeRelationType filteringConditionAgeRelation;

    @Column(nullable = false, length = 20)
    private String company;

    @Column(nullable = false, length = 30)
    private String job;

    @Column(nullable = false, length = 30)
    private String university;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Mbti mbti;

    @Column(nullable = false)
    private boolean smokingYn;

    private boolean filteringConditionSmokingYn;

    @Column(nullable = false)
    private Religion religion;

    private Religion filteringConditionReligion;

    @Column(length = 100)
    private String charmingPoint;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime depositDate;

    @Column(length = 100)
    private String note;

    @ColumnDefault("5")
    private Integer passCount;

    @ColumnDefault("2")
    private Integer chance;

    @Column(length = 20)
    @Convert(converter = ApprovalStatusConverter.class)
    private ApprovalStatus approvalStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActiveStatus status;

    private MemberEntity(final String kakaoId,
                         final String name,
                         final Gender gender,
                         final LocalDateTime birthday,
                         final Integer age,
                         final String phoneNumber,
                         final AgeRelationType filteringConditionAgeRelation,
                         final String company,
                         final String job,
                         final String university,
                         final Mbti mbti,
                         final Boolean smokingYn,
                         final Boolean filteringConditionSmokingYn,
                         final Religion religion,
                         final Religion filteringConditionReligion,
                         final String charmingPoint,
                         final LocalDateTime depositDate,
                         final String note,
                         final Integer passCount,
                         final Integer chance,
                         final ApprovalStatus approvalStatus,
                         final ActiveStatus status
    ) {
        this.kakaoId = kakaoId;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.age = age;
        this.phoneNumber = phoneNumber;
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
            final String name,
            final Gender gender,
            final LocalDateTime birthday,
            final Integer age,
            final String phoneNumber,
            final AgeRelationType filteringConditionAgeRelation,
            final String company,
            final String job,
            final String university,
            final Mbti mbti,
            final Boolean smokingYn,
            final Boolean filteringConditionSmokingYn,
            final Religion religion,
            final Religion filteringConditionReligion,
            final String charmingPoint,
            final LocalDateTime depositDate,
            final String note,
            final Integer passCount,
            final Integer chance,
            final ApprovalStatus approvalStatus,
            final ActiveStatus status
    ) {
        return new MemberEntity(
                kakaoId,
                name,
                gender,
                birthday,
                age,
                phoneNumber,
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

    public Long id() {
        return id;
    }

    public void setAddressId(final Long addressId) {
        this.addressId = addressId;
    }
}
