package com.fofo.core.domain.member;

import com.fofo.core.domain.ActiveStatus;

import java.time.LocalDateTime;

public record UpdateMember(
        String name,
        Gender gender,
        LocalDateTime birthday,
        Integer age,
        AgeRelationType filteringAgeRelation,
        String phoneNumber,
        String company,
        String job,
        String university,
        Mbti mbti,
        Boolean smokingYn,
        Boolean filteringSmoker,
        Religion religion,
        Religion filteringReligion,
        String charmingPoint,
        String note,
        ActiveStatus status
) {
    public static UpdateMember of(final String name,
                                  final Gender gender,
                                  final LocalDateTime birthday,
                                  final int age,
                                  final AgeRelationType filteringAgeRelation,
                                  final String phoneNumber,
                                  final String company,
                                  final String job,
                                  final String university,
                                  final Mbti mbti,
                                  final boolean smokingYn,
                                  final boolean filteringSmoker,
                                  final Religion religion,
                                  final Religion filteringReligion,
                                  final String charmingPoint,
                                  final String note) {
        return new UpdateMember(name,
                gender,
                birthday,
                age,
                filteringAgeRelation,
                phoneNumber,
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
