package com.fofo.core.controller.request;

import com.fofo.core.domain.match.MatchAgreement;
import com.fofo.core.domain.match.MatchingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "매치 상태 변화 DTO")
public record MatchRequestDto(
        @NotNull @Schema(description = "매치 ID", example = "1")
        Long id,
        @Schema(description = "남자 멤버 ID", example = "1")
        Long manId,
        @Schema(description = "남자 동의 여부", example = "false")
        MatchAgreement manAgreement,
        @Schema(description = "여자 멤버 ID", example = "2")
        Long womanId,
        @Schema(description = "여자 동의 여부", example = "true")
        MatchAgreement womanAgreement,
        @NotNull
        @Schema(description = "현재 매치 상태", example = "20")
        MatchingStatus matchingStatus
) {
}
