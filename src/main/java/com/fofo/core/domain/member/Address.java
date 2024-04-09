package com.fofo.core.domain.member;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.storage.AddressEntity;
import org.springframework.data.geo.Point;

import java.time.LocalDateTime;
import java.util.Objects;

public record Address(
        Long id,
        String zipcode,
        String sido,
        String sigungu,
        String eupmyundong,
        String detail,
        String roadNameCd,
        GeoPoint location,
        ActiveStatus status,
        LocalDateTime createdTime,
        LocalDateTime modifiedTime
) {
    public static Address of(final String zipcode,
                             final String sido,
                             final String sigungu,
                             final String eupmyundong,
                             final String detail,
                             final String roadNameCd,
                             final GeoPoint location) {
        return new Address(null, zipcode, sido, sigungu, eupmyundong, detail, roadNameCd, location, ActiveStatus.CREATED, null, null);
    }

    public AddressEntity toEntity() {
        Point location;
        if (Objects.isNull(this.location)) {
            location = null;
        } else {
            location = new Point(this.location);
        }
        return AddressEntity.of(zipcode, sido, sigungu, eupmyundong, detail, roadNameCd, location, status);
    }

}
