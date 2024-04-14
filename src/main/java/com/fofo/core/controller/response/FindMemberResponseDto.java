package com.fofo.core.controller.response;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.member.AgeRelationType;
import com.fofo.core.domain.member.ApprovalStatus;
import com.fofo.core.domain.member.Gender;
import com.fofo.core.domain.member.Mbti;
import com.fofo.core.domain.member.Member;
import com.fofo.core.domain.member.MemberWithAddress;
import com.fofo.core.domain.member.Religion;

import java.time.LocalDateTime;

public record FindMemberResponseDto(
        long id,
        String kakaoId,
        AddressResponseDto address,
        String name,
        Gender gender,
        LocalDateTime birthday,
        int age,
        String phoneNumber,
        AgeRelationType filteringAgeRelation,
        String company,
        String job,
        String university,
        Mbti mbti,
        boolean smokingYn,
        boolean filteringSmoker,
        Religion religion,
        Religion filteringReligion,
        String charmingPoint,
        LocalDateTime depositDate,
        String note,
        int passCount,
        int chance,
        ApprovalStatus approvalStatus,
        ActiveStatus status,
        LocalDateTime createdTime,
        LocalDateTime modifiedTime
) {
    public static FindMemberResponseDto from(final MemberWithAddress memberWithAddress) {
        Member member = memberWithAddress.member();
        return new FindMemberResponseDto(
                member.id(),
                member.kakaoId(),
                AddressResponseDto.from(memberWithAddress.address()),
                member.name(),
                member.gender(),
                member.birthday(),
                member.age(),
                member.phoneNumber(),
                member.filteringAgeRelation(),
                member.company(),
                member.job(),
                member.university(),
                member.mbti(),
                member.smokingYn(),
                member.filteringSmoker(),
                member.religion(),
                member.filteringReligion(),
                member.charmingPoint(),
                member.depositDate(),
                member.note(),
                member.passCount(),
                member.chance(),
                member.approvalStatus(),
                member.status(),
                member.createdTime(),
                member.modifiedTime()
        );
    }

}
