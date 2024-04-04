package com.fofo.core.controller.request;

import com.fofo.core.domain.member.AgeRelationType;
import com.fofo.core.domain.member.Gender;
import com.fofo.core.domain.member.Mbti;
import com.fofo.core.domain.member.Member;
import com.fofo.core.domain.member.Religion;
import com.fofo.core.support.util.AgeUtil;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record MemberRequestDto(
        @NotEmpty
        String kakaoId,
        @NotNull
        AddressRequestDto address,
        @NotEmpty @Size(max=10)
        String name,
        @NotNull
        Gender gender,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime birthday,
        AgeRelationType filteringConditionAgeRelation,
        @NotEmpty @Size(max=20)
        String company,
        @NotEmpty @Size(max=20)
        String job,
        @NotEmpty @Size(max=20)
        String university,
        @NotNull
        Mbti mbti,
        @NotNull
        Boolean smokingYn,
        Boolean filteringConditionSmokingYn,
        @NotNull
        Religion religion,
        Religion filteringConditionReligion,
        String charmingPoint,
        String note
) {

        public Member toDomain() {
                return Member.of(
                        kakaoId,
                        address.toDomain(),
                        name,
                        gender,
                        birthday,
                        AgeUtil.toKoreanAge(birthday),
                        filteringConditionAgeRelation,
                        company,
                        job,
                        university,
                        mbti,
                        smokingYn,
                        filteringConditionSmokingYn,
                        religion,
                        filteringConditionReligion,
                        charmingPoint,
                        null,
                        note,
                        null,
                        null,
                        "승인",
                        "Y"
                );
        }
}
