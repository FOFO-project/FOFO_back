package com.fofo.core.controller.request.MatchRequestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Schema(description = "매칭 취소 요청 DTO")
public record MatchCancelRequestDto(
        @Schema(description = "취소할 매치 ID 리스트", example = "[1,2,3]")
        @NotEmpty
        List<Long> matchIdList
) {}
