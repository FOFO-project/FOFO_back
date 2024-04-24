package com.fofo.core.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "자동 매칭 요청 DTO")
public record AutoMatchRequestDto(
        @Schema(description = "회원 ID 리스트", example = "[]")
        List<Long> memberIdList
) {
}
