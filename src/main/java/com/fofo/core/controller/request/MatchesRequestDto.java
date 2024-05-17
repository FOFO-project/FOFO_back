package com.fofo.core.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record MatchesRequestDto(
        @Schema(description = "성사실패 처리할 매치 ID 리스트", example = "[1,2,3]")
        @NotEmpty
        List<Long> matchIdList
) {
}
