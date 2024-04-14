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
        GeoPoint location,
        ActiveStatus status,
        LocalDateTime createdTime,
        LocalDateTime modifiedTime
) {
    public static Address of(
            final String zipcode,
            final String sido,
            final String sigungu,
            final String eupmyundong,
            final GeoPoint location) {
        return new Address(null, zipcode, sido, sigungu, eupmyundong, location, ActiveStatus.CREATED, null, null);
    }

    public static Address from(final AddressEntity addressEntity) {
        return new Address(
                addressEntity.getId(),
                addressEntity.getZipCode(),
                addressEntity.getSido(),
                addressEntity.getSigungu(),
                addressEntity.getEupmyundong(),
                new GeoPoint(addressEntity.getLocation()),
                addressEntity.getStatus(),
                addressEntity.getCreatedTime(),
                addressEntity.getUpdatedTime());
    }

    public AddressEntity toEntity() {
        Point location;
        if (Objects.isNull(this.location)) {
            location = null;
        } else {
            location = new Point(this.location);
        }
        return AddressEntity.of(zipcode, sido, sigungu, eupmyundong, location, status);
    }

}
