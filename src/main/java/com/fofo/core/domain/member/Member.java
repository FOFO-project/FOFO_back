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
        int age,
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
            final int age,
            final String phoneNumber,
            final AgeRelationType filteringConditionAgeRelation,
            final String company,
            final String job,
            final String university,
            final Mbti mbti,
            final Boolean smokingYn,
            final Boolean filteringSmoker,
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
                status);
    }

    public static Member from(MemberEntity memberEntity) {
        return new Member(
                memberEntity.getId(),
                memberEntity.getKakaoId(),
                memberEntity.getName(),
                memberEntity.getGender(),
                memberEntity.getBirthday(),
                memberEntity.getAge(),
                memberEntity.getPhoneNumber(),
                memberEntity.getFilteringConditionAgeRelation(),
                memberEntity.getCompany(),
                memberEntity.getJob(),
                memberEntity.getUniversity(),
                memberEntity.getMbti(),
                memberEntity.isSmokingYn(),
                memberEntity.isFilteringSmoker(),
                memberEntity.getReligion(),
                memberEntity.getFilteringConditionReligion(),
                memberEntity.getCharmingPoint(),
                memberEntity.getDepositDate(),
                memberEntity.getNote(),
                memberEntity.getPassCount(),
                memberEntity.getChance(),
                memberEntity.getApprovalStatus(),
                memberEntity.getStatus(),
                memberEntity.getCreatedTime(),
                memberEntity.getUpdatedTime()
        );
    }
}
