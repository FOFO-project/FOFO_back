package com.fofo.core.storage;

import com.querydsl.core.annotations.QueryProjection;
import org.springframework.data.geo.Point;

public record MatchResultAddressDto (
        String zipCode,
        String sido,
        String sigungu,
        String eupmyundong,
        Point location
){

    @QueryProjection
    public MatchResultAddressDto(String zipCode, String sido, String sigungu, String eupmyundong, Point location) {
        this.zipCode = zipCode;
        this.sido = sido;
        this.sigungu = sigungu;
        this.eupmyundong = eupmyundong;
        this.location = location;
    }

    public static QMatchResultAddressDto from(QAddressEntity address){
        return new QMatchResultAddressDto(
                address.zipCode,
                address.sido,
                address.sigungu,
                address.eupmyundong,
                address.location
        );
    }
}
