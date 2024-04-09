package com.fofo.core.controller.request;

import com.fofo.core.domain.member.Address;
import com.fofo.core.domain.member.GeoPoint;
import jakarta.validation.constraints.Size;

public record AppendAddressRequestDto(
        @Size(max = 5)
        String zipcode,
        @Size(max = 20)
        String sido,
        @Size(max = 20)
        String sigungu,
        @Size(max = 20)
        String eupmyundong,
        @Size(max = 300)
        String detail,
        @Size(max = 12)
        String roadNameCd,
        GeoPoint location
) {
    public Address toDomain() {
        return Address.of(zipcode, sido, sigungu, eupmyundong, detail, roadNameCd, location);
    }

}
