package com.fofo.core.domain.member;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.storage.AddressEntity;

import java.time.LocalDateTime;

public record Address(
        Long id,
        String zipcode,
        String sido,
        String sigungu,
        ActiveStatus status,
        LocalDateTime createdTime,
        LocalDateTime modifiedTime
) {
    public static Address of(
            final String zipcode,
            final String sido,
            final String sigungu) {
        return new Address(null, zipcode, sido, sigungu, ActiveStatus.CREATED, null, null);
    }

    public static Address from(final AddressEntity addressEntity) {
        return new Address(
                addressEntity.getId(),
                addressEntity.getZipCode(),
                addressEntity.getSido(),
                addressEntity.getSigungu(),
                addressEntity.getStatus(),
                addressEntity.getCreatedTime(),
                addressEntity.getUpdatedTime());
    }

    public AddressEntity toEntity() {
        return AddressEntity.of(zipcode, sido, sigungu, status);
    }

}
