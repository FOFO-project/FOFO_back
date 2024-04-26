package com.fofo.core.controller.request;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.member.Address;
import com.fofo.core.domain.member.AgeRelationType;
import com.fofo.core.domain.member.ApprovalStatus;
import com.fofo.core.domain.member.FilteringSmoker;
import com.fofo.core.domain.member.Gender;
import com.fofo.core.domain.member.MatchableYn;
import com.fofo.core.domain.member.Mbti;
import com.fofo.core.domain.member.Member;
import com.fofo.core.domain.member.Religion;
import com.fofo.core.domain.member.SmokingYn;
import com.fofo.core.support.util.AgeUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "멤버 등록 요청")
public record AppendMemberRequestDto(
        @Schema(description = "카카오톡 ID", example = "opr9982")
        @NotEmpty @Size(max = 20)
        String kakaoId,
        @Valid @NotNull
        AddressRequestDto address,
        @Schema(description = "이름", example = "황성준")
        @NotEmpty @Size(max=10)
        String name,
        @Schema(description = "성별")
        @NotNull
        Gender gender,
        @Schema(description = "생년월일", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime birthday,
        @Schema(description = "키", example = "180")
        @Positive
        int height,
        @Schema(description = "휴대전화번호", example = "01012345678")
        @NotEmpty @Pattern(regexp = "^01([0|1|6|7|8|9])(-)?([0-9]{3,4})(-)?([0-9]{4})$")
        String phoneNumber,
        @Schema(description = "절대 안되는 연령 관계")
        AgeRelationType filteringAgeRelation,
        @Schema(description = "회사", example = "한화시스템")
        @NotEmpty @Size(max=20)
        String company,
        @Schema(description = "직업", example = "백엔드 개발자")
        @NotEmpty @Size(max=20)
        String job,
        @Schema(description = "(출신)대학교", example = "단국대학교")
        @NotEmpty @Size(max=20)
        String university,
        @Schema(description = "mbti")
        @NotNull
        Mbti mbti,
        @Schema(description = "흡연 여부", example = "N")
        @NotNull
        SmokingYn smokingYn,
        @Schema(description = "절대 안되는 흡연 조건", example = "N")
        @NotNull
        FilteringSmoker filteringSmoker,
        @Schema(description = "종교")
        @NotNull
        Religion religion,
        @Schema(description = "절대 안되는 종교 조건")
        Religion filteringReligion,
        @Schema(description = "어필 포인트", example = "저는 잘생겼습니다.")
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
                        height,
                        phoneNumber,
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
                        ApprovalStatus.DEPOSIT_PENDING,
                        MatchableYn.N,
                        ActiveStatus.CREATED
                );
        }

        public Address toAddress() {
                return Address.of(
                        address.zipcode(),
                        address.sido(),
                        address.sigungu(),
                        address.eupmyundong(),
                        address.location()
                );
        }
}
