package com.fofo.core.controller.request.MatchRequestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "수동 매칭 요청 DTO")
public record ManualMatchRequestDto(
        @Schema(description = "남성 회원 ID", example = "1")
        @NotNull
        Long manMemberId,
        @Schema(description = "여성 회원 ID", example = "2")
        @NotNull
        Long womanMemberId
) {

}
