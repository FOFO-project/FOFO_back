package com.fofo.core.controller.request;

import com.fofo.core.domain.member.AgeRelationType;
import com.fofo.core.domain.member.ApprovalStatus;
import com.fofo.core.domain.member.FindMember;
import com.fofo.core.domain.member.Gender;
import com.fofo.core.domain.member.Mbti;
import com.fofo.core.domain.member.Religion;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record FindMembersConditionDto(
        String kakaoId,
        String name,
        Gender gender,
        @DateTimeFormat(pattern = "yyyy")
        LocalDate yearOfBirthday,
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

    public FindMember toFindMember() {
        return FindMember.of(
                kakaoId,
                name,
                gender,
                yearOfBirthday,
                filteringConditionAgeRelation,
                company,
                job,
                university,
                mbti,
                smokingYn,
                filteringSmoker,
                religion,
                filteringConditionReligion,
                approvalStatus
                );
    }
}
