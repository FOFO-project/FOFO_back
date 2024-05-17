package com.fofo.core.domain.member;

import com.fofo.core.domain.ActiveStatus;

import java.time.LocalDateTime;

public record UpdateMember(
        String name,
        Gender gender,
        LocalDateTime birthday,
        Integer age,
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
        String charmingPoint,
        String note,
        ActiveStatus status
) {
    public static UpdateMember of(final String name,
                                  final Gender gender,
                                  final LocalDateTime birthday,
                                  final Integer age,
                                  final Integer height,
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
                                  final String note) {
        return new UpdateMember(name,
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
                note,
                ActiveStatus.UPDATED);
    }

}
