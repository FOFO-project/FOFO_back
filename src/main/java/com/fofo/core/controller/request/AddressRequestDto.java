package com.fofo.core.controller.request;

import com.fofo.core.domain.member.Address;
import com.fofo.core.domain.member.GeoPoint;

public record AddressRequestDto(
        String zipcode,
        String sido,
        String sigungu,
        String eupmyundong,
        String detail,
        String roadNameCd,
        GeoPoint location
) {
    public Address toDomain() {
        return Address.of(zipcode, sido, sigungu, eupmyundong, detail, roadNameCd, location);
    }

}
