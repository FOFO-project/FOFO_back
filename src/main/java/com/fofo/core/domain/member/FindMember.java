package com.fofo.core.domain.member;

import com.fofo.core.domain.match.MatchingStatus;

import java.time.LocalDate;

public record FindMember(
        String kakaoId,
        String name,
        Gender gender,
        LocalDate yearOfBirthday,
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
        String zipcode,
        String sido,
        String sigungu,
        String eupmyundong,
        MatchingStatus matchingStatus
) {

    public static FindMember of(final String kakaoId,
                                final String name,
                                final Gender gender,
                                final LocalDate birthday,
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
                                final String zipcode,
                                final String sido,
                                final String sigungu,
                                final String eupmyundong,
                                final MatchingStatus matchingStatus
    ) {
        return new FindMember(
                kakaoId,
                name,
                gender,
                birthday,
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
                zipcode,
                sido,
                sigungu,
                eupmyundong,
                matchingStatus);
    }
}
