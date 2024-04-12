package com.fofo.core.storage;

import com.fofo.core.domain.member.AgeRelationType;
import com.fofo.core.domain.member.Gender;
import com.fofo.core.domain.member.Mbti;
import com.fofo.core.domain.member.Religion;
import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

public record MatchResultMemberDto (
    String kakaoId,
    MatchResultAddressDto address,
    String name,
    Gender gender,
    LocalDateTime birthday,
    Integer age,
    String phoneNumber,
    AgeRelationType filteringConditionAgeRelation,
    String company,
    String job,
    String university,
    Mbti mbti,
    boolean smokingYn,
    boolean filteringSmoker,
    Religion religion,
    Religion filteringConditionReligion,
    String charmingPoint,
    LocalDateTime depositDate,
    String note,
    Integer passCount,
    Integer chance,
    String imageUrl
){
    @QueryProjection
    public MatchResultMemberDto(String kakaoId, MatchResultAddressDto address, String name, Gender gender, LocalDateTime birthday, Integer age, String phoneNumber, AgeRelationType filteringConditionAgeRelation, String company, String job, String university, Mbti mbti, boolean smokingYn, boolean filteringSmoker, Religion religion, Religion filteringConditionReligion, String charmingPoint, LocalDateTime depositDate, String note, Integer passCount, Integer chance, String imageUrl) {
        this.kakaoId = kakaoId;
        this.address = address;
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
        this.filteringSmoker = filteringSmoker;
        this.religion = religion;
        this.filteringConditionReligion = filteringConditionReligion;
        this.charmingPoint = charmingPoint;
        this.depositDate = depositDate;
        this.note = note;
        this.passCount = passCount;
        this.chance = chance;
        this.imageUrl = imageUrl;
    }

    public static QMatchResultMemberDto from(QMemberEntity member, QAddressEntity address) {
        return new QMatchResultMemberDto(
                member.kakaoId,
                MatchResultAddressDto.from(address),
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
                member.filteringSmoker,
                member.religion,
                member.filteringConditionReligion,
                member.charmingPoint,
                member.depositDate,
                member.note,
                member.passCount,
                member.chance,
                member.note
        );
    }
}
