package com.fofo.core.controller.response;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.image.Image;
import com.fofo.core.domain.image.ImageType;
import com.fofo.core.domain.member.AgeRelationType;
import com.fofo.core.domain.member.ApprovalStatus;
import com.fofo.core.domain.member.FilteringSmoker;
import com.fofo.core.domain.member.Form;
import com.fofo.core.domain.member.Gender;
import com.fofo.core.domain.member.MatchableYn;
import com.fofo.core.domain.member.Mbti;
import com.fofo.core.domain.member.Member;
import com.fofo.core.domain.member.Religion;
import com.fofo.core.domain.member.SmokingYn;

import java.time.LocalDateTime;
import java.util.List;

public record FindMemberResponseDto(
        long id,
        String kakaoId,
        AddressResponseDto address,
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
        int passCount,
        int chance,
        ApprovalStatus approvalStatus,
        MatchableYn matchableYn,
        ActiveStatus status,
        List<Long> userProfileImageIds,
        Long profileImageId,
        LocalDateTime createdTime,
        LocalDateTime modifiedTime
) {
    public static FindMemberResponseDto from(final Form form) {
        Member member = form.memberWithAddress().member();
        List<Image> images = form.images();
        List<Long> userProfileImageIds = images.stream()
                .filter(image -> ImageType.USER_PROFILE == image.type())
                .map(Image::id)
                .toList();
        Long profileImageId = images.stream()
                .filter(image -> ImageType.PROFILE_CARD == image.type())
                .map(Image::id)
                .findAny()
                .orElse(null);

        return new FindMemberResponseDto(
                member.id(),
                member.kakaoId(),
                AddressResponseDto.from(form.memberWithAddress().address()),
                member.name(),
                member.gender(),
                member.birthday(),
                member.age(),
                member.height(),
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
                member.matchableYn(),
                member.status(),
                userProfileImageIds,
                profileImageId,
                member.createdTime(),
                member.modifiedTime()
        );
    }

}
