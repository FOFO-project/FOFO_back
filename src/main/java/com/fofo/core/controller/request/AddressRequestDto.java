package com.fofo.core.controller.request;

import com.fofo.core.domain.member.GeoPoint;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

@Schema(description = "주소 등록 요청")
public record AddressRequestDto(
        @Schema(description = "우편번호", example = "07551")
        @Size(max = 5)
        String zipcode,
        @Schema(description = "시도", example = "서울특별시")
        @Size(max = 20)
        String sido,
        @Schema(description = "시군구", example = "강서구")
        @Size(max = 20)
        String sigungu,
        @Schema(description = "읍면동", example = "등촌동")
        @Size(max = 20)
        String eupmyundong,
        @Schema(description = "위도,경도")
        GeoPoint location
) {
}
