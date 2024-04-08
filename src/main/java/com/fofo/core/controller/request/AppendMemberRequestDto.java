package com.fofo.core.controller.request;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.member.Address;
import com.fofo.core.domain.member.AgeRelationType;
import com.fofo.core.domain.member.ApprovalStatus;
import com.fofo.core.domain.member.Gender;
import com.fofo.core.domain.member.Mbti;
import com.fofo.core.domain.member.Member;
import com.fofo.core.domain.member.Religion;
import com.fofo.core.support.util.AgeUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record AppendMemberRequestDto(
        @NotEmpty @Size(max = 20)
        String kakaoId,
        @Valid @NotNull
        AppendAddressRequestDto address,
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
        boolean smokingYn,
        boolean filteringConditionSmokingYn,
        @NotNull
        Religion religion,
        Religion filteringConditionReligion,
        @Size(max=100)
        String charmingPoint
) {

        public Member toMember() {
                return Member.of(
                        kakaoId,
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
                        null,
                        null,
                        ApprovalStatus.DEPOSIT_PENDING,
                        ActiveStatus.CREATED
                );
        }

        public Address toAddress() {
                return Address.of(
                        address.zipcode(),
                        address.sido(),
                        address.sigungu(),
                        address.eupmyundong(),
                        address.detail(),
                        address.roadNameCd(),
                        address.location()
                );
        }
}
