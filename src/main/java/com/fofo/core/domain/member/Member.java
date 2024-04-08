package com.fofo.core.domain.member;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.storage.MemberEntity;

import java.time.LocalDateTime;

public record Member(
        Long id,
        String kakaoId,
        String name,
        Gender gender,
        LocalDateTime birthday,
        Integer age,
        AgeRelationType filteringConditionAgeRelation,
        String company,
        String job,
        String university,
        Mbti mbti,
        boolean smokingYn,
        boolean filteringConditionSmokingYn,
        Religion religion,
        Religion filteringConditionReligion,
        String charmingPoint,
        LocalDateTime depositDate,
        String note,
        Integer passCount,
        Integer chance,
        ApprovalStatus approvalStatus,
        ActiveStatus status,
        LocalDateTime createdTime,
        LocalDateTime modifiedTime
) {

    public static Member of(
            final String kakaoId,
            final String name,
            final Gender gender,
            final LocalDateTime birthday,
            final Integer age,
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
            final Integer passCount,
            final Integer chance,
            final ApprovalStatus approvalStatus,
            final ActiveStatus status
    ) {
        return new Member(
                null,
                kakaoId,
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
                "",
                passCount,
                chance,
                approvalStatus,
                status,
                null,
                null);
    }

    public MemberEntity toEntity() {
        return MemberEntity.of(kakaoId,
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
