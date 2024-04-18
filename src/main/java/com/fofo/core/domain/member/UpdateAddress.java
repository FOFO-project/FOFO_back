package com.fofo.core.domain.member;

import com.fofo.core.domain.ActiveStatus;

public record UpdateAddress(
        String zipcode,
        String sido,
        String sigungu,
        String eupmyundong,
        GeoPoint location,
        ActiveStatus status
) {
    public static UpdateAddress of(final String zipcode,
                                   final String sido,
                                   final String sigungu,
                                   final String eupmyundong,
                                   final GeoPoint location) {
        return new UpdateAddress(zipcode, sido, sigungu, eupmyundong, location, ActiveStatus.UPDATED);
    }
}
