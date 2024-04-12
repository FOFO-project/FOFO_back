package com.fofo.core.storage;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.member.AgeRelationType;
import com.fofo.core.domain.member.ApprovalStatus;
import com.fofo.core.domain.member.Gender;
import com.fofo.core.domain.member.Mbti;
import com.fofo.core.domain.member.Religion;
import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

public class MatchResultMemberDto {
    private Long id;
    private String kakaoId;
    private Long addressId;
    private String name;
    private Gender gender;
    private LocalDateTime birthday;
    private Integer age;
    private String phoneNumber;
    private AgeRelationType filteringConditionAgeRelation;
    private String company;
    private String job;
    private String university;
    private Mbti mbti;
    private boolean smokingYn;
    private boolean filteringConditionSmokingYn;
    private Religion religion;
    private Religion filteringConditionReligion;
    private String charmingPoint;
    private LocalDateTime depositDate;
    private String note;
    private Integer passCount;
    private Integer chance;
    private ApprovalStatus approvalStatus;
    private ActiveStatus status;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    @QueryProjection
    public MatchResultMemberDto(Long id, String kakaoId, Long addressId, String name, Gender gender, LocalDateTime birthday, Integer age, String phoneNumber, AgeRelationType filteringConditionAgeRelation, String company, String job, String university, Mbti mbti, boolean smokingYn, boolean filteringConditionSmokingYn, Religion religion, Religion filteringConditionReligion, String charmingPoint, LocalDateTime depositDate, String note, Integer passCount, Integer chance, ApprovalStatus approvalStatus, ActiveStatus status, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.id = id;
        this.kakaoId = kakaoId;
        this.addressId = addressId;
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
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public static QMatchResultMemberDto getQMatchMemberResultDto(QMemberEntity member) {
        return new QMatchResultMemberDto(
                member.id,
                member.kakaoId,
                member.addressId,
                member.name,
                member.gender,
                member.birthday,
                member.age,
                member.phoneNumber,
                member.filteringConditionAgeRelation,
                member.company,
                member.job,
                member.university,
                member.mbti,
                member.smokingYn,
                member.filteringConditionSmokingYn,
                member.religion,
                member.filteringConditionReligion,
                member.charmingPoint,
                member.depositDate,
                member.note,
                member.passCount,
                member.chance,
                member.approvalStatus,
                member.status,
                member.createdTime,
                member.updatedTime
        );
    }
}
