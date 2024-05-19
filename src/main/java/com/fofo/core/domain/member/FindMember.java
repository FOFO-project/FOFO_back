package com.fofo.core.domain.member;

import com.fofo.core.domain.match.MatchingStatus;

import java.time.LocalDate;

public record FindMember(
        String kakaoId,
        String name,
        Gender gender,
        LocalDate yearOfBirthday,
        Integer height,
        AgeRelationType filteringAgeRelation,
        String company,
        String job,
        String university,
        Mbti mbti,
        SmokingYn smokingYn,
        FilteringSmoker filteringSmoker,
        Religion religion,
        Religion filteringReligion,
        ApprovalStatus approvalStatus,
        MatchableYn matchableYn,
        String sido,
        String sigungu,
        MatchingStatus matchingStatus
) {

    public static FindMember of(final String kakaoId,
                                final String name,
                                final Gender gender,
                                final LocalDate birthday,
                                final Integer height,
                                final AgeRelationType filteringAgeRelation,
                                final String company,
                                final String job,
                                final String university,
                                final Mbti mbti,
                                final SmokingYn smokingYn,
                                final FilteringSmoker filteringSmoker,
                                final Religion religion,
                                final Religion filteringConditionReligion,
                                final ApprovalStatus approvalStatus,
                                final MatchableYn matchableYn,
                                final String sido,
                                final String sigungu,
                                final MatchingStatus matchingStatus
    ) {
        return new FindMember(
                kakaoId,
                name,
                gender,
                birthday,
                height,
                filteringAgeRelation,
                company,
                job,
                university,
                mbti,
                smokingYn,
                filteringSmoker,
                religion,
                filteringConditionReligion,
                approvalStatus,
                matchableYn,
                sido,
                sigungu,
                matchingStatus);
    }
}
