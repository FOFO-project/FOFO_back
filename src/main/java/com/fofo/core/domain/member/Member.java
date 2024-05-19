package com.fofo.core.domain.member;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.storage.MemberEntity;

import java.time.LocalDateTime;

import static com.fofo.core.support.constant.MemberConstants.DEFAULT_CHANCE;
import static com.fofo.core.support.constant.MemberConstants.DEFAULT_PASS_COUNT;

public record Member(
        Long id,
        String kakaoId,
        String name,
        Gender gender,
        LocalDateTime birthday,
        int age,
        int height,
        AgeRelationType filteringAgeRelation,
        String company,
        String job,
        String university,
        Mbti mbti,
        SmokingYn smokingYn,
        FilteringSmoker filteringSmoker,
        Religion religion,
        Religion filteringReligion,
        String charmingPoint,
        LocalDateTime depositDate,
        String note,
        Integer passCount,
        Integer chance,
        ApprovalStatus approvalStatus,
        MatchableYn matchableYn,
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
            final int height,
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
            final ApprovalStatus approvalStatus,
            final MatchableYn matchableYn,
            final ActiveStatus status
    ) {
        return new Member(
                null,
                kakaoId,
                name,
                gender,
                birthday,
                age,
                height,
                filteringAgeRelation,
                company,
                job,
                university,
                mbti,
                smokingYn,
                filteringSmoker,
                religion,
                filteringReligion,
                charmingPoint,
                null,
                "",
                DEFAULT_PASS_COUNT,
                DEFAULT_CHANCE,
                approvalStatus,
                matchableYn,
                status,
                null,
                null);
    }

    public static Member from(final MemberEntity memberEntity) {
        return new Member(
                memberEntity.getId(),
                memberEntity.getKakaoId(),
                memberEntity.getName(),
                memberEntity.getGender(),
                memberEntity.getBirthday(),
                memberEntity.getAge(),
                memberEntity.getHeight(),
                memberEntity.getFilteringAgeRelation(),
                memberEntity.getCompany(),
                memberEntity.getJob(),
                memberEntity.getUniversity(),
                memberEntity.getMbti(),
                memberEntity.getSmokingYn(),
                memberEntity.getFilteringSmoker(),
                memberEntity.getReligion(),
                memberEntity.getFilteringReligion(),
                memberEntity.getCharmingPoint(),
                memberEntity.getDepositDate(),
                memberEntity.getNote(),
                memberEntity.getPassCount(),
                memberEntity.getChance(),
                memberEntity.getApprovalStatus(),
                memberEntity.getMatchableYn(),
                memberEntity.getStatus(),
                memberEntity.getCreatedTime(),
                memberEntity.getUpdatedTime());
    }

    public MemberEntity toEntity() {
        return MemberEntity.of(
                kakaoId,
                name,
                gender,
                birthday,
                age,
                height,
                filteringAgeRelation,
                company,
                job,
                university,
                mbti,
                smokingYn,
                filteringSmoker,
                religion,
                filteringReligion,
                charmingPoint,
                depositDate,
                note,
                passCount,
                chance,
                approvalStatus,
                matchableYn,
                status);
    }
}
