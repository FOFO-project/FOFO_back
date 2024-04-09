package com.fofo.core.controller.request.MatchRequestDto;

import com.fofo.core.domain.match.MatchingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(description = "매치 다음 단계 요청 DTO")
public record MatchRequestDto(
        @Schema(description = "매치 ID 리스트", example = "[1,2,3]")
        @NotEmpty
        List<Long> matchIdList,
        @Schema(description = "현재 매치 상태", example = "10")
        @NotNull
        MatchingStatus matchingStatus
) {
}
