package com.fofo.core.controller.request;

import com.fofo.core.domain.member.AgeRelationType;
import com.fofo.core.domain.member.FilteringSmoker;
import com.fofo.core.domain.member.Gender;
import com.fofo.core.domain.member.Mbti;
import com.fofo.core.domain.member.Religion;
import com.fofo.core.domain.member.SmokingYn;
import com.fofo.core.domain.member.UpdateAddress;
import com.fofo.core.domain.member.UpdateMember;
import com.fofo.core.support.util.AgeUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Schema(description = "멤버 수정 요청")
public record UpdateMemberRequestDto(
        @Schema(description = "시도", example = "서울특별시")
        @Size(max = 20)
        String sido,
        @Schema(description = "시군구", example = "강서구")
        @Size(max = 20)
        String sigungu,
        @Schema(description = "이름", example = "황성준")
        @Size(max=10)
        String name,
        @Schema(description = "성별")
        Gender gender,
        @Schema(description = "생년월일", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime birthday,
        @Schema(description = "키", example = "180")
        @Positive
        Integer height,
        @Schema(description = "연령 관계")
        AgeRelationType filteringAgeRelation,
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
        @Schema(description = "흡연 여부", example = "Y")
        SmokingYn smokingYn,
        @Schema(description = "상대 흡연 조건", example = "Y")
        FilteringSmoker filteringSmoker,
        @Schema(description = "종교")
        Religion religion,
        @Schema(description = "상대 종교 조건")
        Religion filteringReligion,
        @Schema(description = "어필 포인트", example = "저는 잘생겼습니다.")
        @Size(max=100)
        String charmingPoint,
        @Schema(description = "비고", example = "이 회원은 요주의 회원입니다.")
        @Size(max=100)
        String note,
        @Schema(description = "카드 이미지")
        MultipartFile profileCardImage
) {
        public UpdateMember toUpdateMember() {
                return UpdateMember.of(name,
                        gender,
                        birthday,
                        AgeUtil.toKoreanAge(birthday),
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
                        note);
        }

        public UpdateAddress toUpdateAddress() {
                return UpdateAddress.of(sido, sigungu);
        }
}
