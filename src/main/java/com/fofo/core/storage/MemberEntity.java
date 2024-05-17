package com.fofo.core.storage;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.member.AgeRelationType;
import com.fofo.core.domain.member.ApprovalStatus;
import com.fofo.core.domain.member.FilteringSmoker;
import com.fofo.core.domain.member.Gender;
import com.fofo.core.domain.member.MatchableYn;
import com.fofo.core.domain.member.Mbti;
import com.fofo.core.domain.member.Religion;
import com.fofo.core.domain.member.SmokingYn;
import com.fofo.core.storage.converter.ActiveStatusConverter;
import com.fofo.core.storage.converter.AgeRelationTypeConverter;
import com.fofo.core.storage.converter.ApprovalStatusConverter;
import com.fofo.core.storage.converter.FilteringSmokerConverter;
import com.fofo.core.storage.converter.GenderConverter;
import com.fofo.core.storage.converter.MatchableYnConverter;
import com.fofo.core.storage.converter.ReligionConverter;
import com.fofo.core.storage.converter.SmokingYnConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.fofo.core.support.constant.MemberConstants.DEFAULT_CHANCE;
import static com.fofo.core.support.constant.MemberConstants.DEFAULT_PASS_COUNT;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member", indexes = {
        @Index(columnList = "kakaoId", unique = true),
        @Index(columnList = "addressId")
})
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

    @Column(nullable = false, length = 10)
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @Column(nullable = false)
    private LocalDateTime birthday;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Integer height;

    @Column(nullable = false, length = 50)
    private String phoneNumber;

    @Column(length = 10)
    @Convert(converter = AgeRelationTypeConverter.class)
    private AgeRelationType filteringAgeRelation;

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
    @Convert(converter = SmokingYnConverter.class)
    private SmokingYn smokingYn;

    @Column(nullable = false)
    @Convert(converter = FilteringSmokerConverter.class)
    private FilteringSmoker filteringSmoker;

    @Column(nullable = false, length = 20)
    @Convert(converter = ReligionConverter.class)
    private Religion religion;

    @Column(length = 20)
    @Convert(converter = ReligionConverter.class)
    private Religion filteringReligion;

    @Column(length = 100)
    private String charmingPoint;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime depositDate;

    @Column(length = 100)
    private String note;

    @Setter
    @Column(nullable = false, columnDefinition = "integer default 4")
    private int passCount;

    @Setter
    @Column(nullable = false, columnDefinition = "integer default 2")
    private int chance;

    @Column(nullable = false, length = 20)
    @Convert(converter = ApprovalStatusConverter.class)
    private ApprovalStatus approvalStatus;

    @Column(nullable = false, length = 20)
    @Convert(converter = MatchableYnConverter.class)
    private MatchableYn matchableYn;

    @Column(nullable = false, length = 20)
    @Convert(converter = ActiveStatusConverter.class)
    private ActiveStatus status;

    public static MemberEntity of(
            final String kakaoId,
            final String name,
            final Gender gender,
            final LocalDateTime birthday,
            final Integer age,
            final Integer height,
            final String phoneNumber,
            final AgeRelationType filteringConditionAgeRelation,
            final String company,
            final String job,
            final String university,
            final Mbti mbti,
            final SmokingYn smokingYn,
            final FilteringSmoker filteringSmoker,
            final Religion religion,
            final Religion filteringConditionReligion,
            final String charmingPoint,
            final LocalDateTime depositDate,
            final String note,
            final Integer passCount,
            final Integer chance,
            final ApprovalStatus approvalStatus,
            final MatchableYn matchableYn,
            final ActiveStatus status
    ) {
        return new MemberEntity(
                kakaoId,
                name,
                gender,
                birthday,
                age,
                height,
                phoneNumber,
                filteringConditionAgeRelation,
                company,
                job,
                university,
                mbti,
                smokingYn,
                filteringSmoker,
                religion,
                filteringConditionReligion,
                charmingPoint,
                depositDate,
                note,
                passCount,
                chance,
                approvalStatus,
                matchableYn,
                status);
    }


    private MemberEntity(
            final String kakaoId,
            final String name,
            final Gender gender,
            final LocalDateTime birthday,
            final Integer age,
            final Integer height,
            final String phoneNumber,
            final AgeRelationType filteringAgeRelation,
            final String company,
            final String job,
            final String university,
            final Mbti mbti,
            final SmokingYn smokingYn,
            final FilteringSmoker filteringSmoker,
            final Religion religion,
            final Religion filteringReligion,
            final String charmingPoint,
            final LocalDateTime depositDate,
            final String note,
            final Integer passCount,
            final Integer chance,
            final ApprovalStatus approvalStatus,
            final MatchableYn matchableYn,
            final ActiveStatus status
    ) {
        this.kakaoId = kakaoId;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.age = age;
        this.height = height;
        this.phoneNumber = phoneNumber;
        this.filteringAgeRelation = filteringAgeRelation;
        this.company = company;
        this.job = job;
        this.university = university;
        this.mbti = mbti;
        this.smokingYn = smokingYn;
        this.filteringSmoker = filteringSmoker;
        this.religion = religion;
        this.filteringReligion = filteringReligion;
        this.charmingPoint = charmingPoint;
        this.depositDate = depositDate;
        this.note = note;
        this.passCount = passCount;
        this.chance = chance;
        this.approvalStatus = approvalStatus;
        this.matchableYn = matchableYn;
        this.status = status;
    }

    public void decreaseChance() {
        chance--;
    }

    public void usePassCount() {
        passCount--;
        matchableYn = MatchableYn.Y;
        if (passCount == 0) {
            if (chance == 1) {
                chance = 0;
                toDepositPendingStatus();
            } else {
                chance--;
                passCount = DEFAULT_PASS_COUNT;
            }
        }
    }

    public void toDepositPendingStatus() {
        depositDate = null;
        approvalStatus = ApprovalStatus.DEPOSIT_PENDING;
        matchableYn = MatchableYn.N;
        chance = DEFAULT_CHANCE;
        passCount = DEFAULT_PASS_COUNT;
    }
}
