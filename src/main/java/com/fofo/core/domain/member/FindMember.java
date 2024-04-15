package com.fofo.core.domain.member;

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
        Boolean smokingYn,
        Boolean filteringSmoker,
        Religion religion,
        Religion filteringReligion,
        ApprovalStatus approvalStatus
) {

    public static FindMember of(
            String kakaoId,
            String name,
            Gender gender,
            LocalDate birthday,
            AgeRelationType filteringConditionAgeRelation,
            String company,
            String job,
            String university,
            Mbti mbti,
            Boolean smokingYn,
            Boolean filteringSmoker,
            Religion religion,
            Religion filteringConditionReligion,
            ApprovalStatus approvalStatus
    ) {
        return new FindMember(
                kakaoId,
                name,
                gender,
                birthday,
                filteringConditionAgeRelation,
                company,
                job,
                university,
                mbti,
                smokingYn,
                filteringSmoker,
                religion,
                filteringConditionReligion,
                approvalStatus);
    }
}
