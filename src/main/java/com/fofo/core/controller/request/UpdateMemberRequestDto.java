package com.fofo.core.controller.request;

import com.fofo.core.domain.member.AgeRelationType;
import com.fofo.core.domain.member.Gender;
import com.fofo.core.domain.member.Mbti;
import com.fofo.core.domain.member.Religion;
import com.fofo.core.domain.member.UpdateAddress;
import com.fofo.core.domain.member.UpdateMember;
import com.fofo.core.support.util.AgeUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "멤버 수정 요청")
public record UpdateMemberRequestDto(
        @Valid
        AddressRequestDto address,
        @Schema(description = "이름", example = "황성준")
        @Size(max=10)
        String name,
        @Schema(description = "성별")
        Gender gender,
        @Schema(description = "생년월일", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime birthday,
        @Schema(description = "연령 관계")
        AgeRelationType filteringAgeRelation,
        @Schema(description = "휴대전화번호", example = "01012345678")
        @Pattern(regexp = "^01([0|1|6|7|8|9])(-)?([0-9]{3,4})(-)?([0-9]{4})$")
        String phoneNumber,
        @Schema(description = "회사", example = "한화시스템")
        @Size(max=20)
        String company,
        @Schema(description = "직업", example = "백엔드 개발자")
        @Size(max=20)
        String job,
        @Schema(description = "(출신)대학교", example = "단국대학교")
        @Size(max=20)
        String university,
        @Schema(description = "mbti")
        Mbti mbti,
        @Schema(description = "흡연 여부", example = "false")
        Boolean smokingYn,
        @Schema(description = "상대 흡연 조건", example = "false")
        Boolean filteringSmoker,
        @Schema(description = "종교")
        Religion religion,
        @Schema(description = "상대 종교 조건")
        Religion filteringReligion,
        @Schema(description = "어필 포인트", example = "저는 잘생겼습니다.")
        @Size(max=100)
        String charmingPoint,
        @Schema(description = "비고", example = "이 회원은 요주의 회원입니다.")
        @Size(max=100)
        String note
) {
        public UpdateMember toUpdateMember() {
                return UpdateMember.of(name,
                        gender,
                        birthday,
                        AgeUtil.toKoreanAge(birthday),
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
                        note);
        }

        public UpdateAddress toUpdateAddress() {
                if (address == null) {
                        return null;
                }
                return UpdateAddress.of(address.zipcode(),
                        address.sido(),
                        address.sigungu(),
                        address.eupmyundong(),
                        address.location());
        }
}
