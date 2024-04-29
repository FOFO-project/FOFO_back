package com.fofo.core.controller.response;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.member.Address;

import java.time.LocalDateTime;

public record AddressResponseDto(
        Long id,
        String zipcode,
        String sido,
        String sigungu,
        String eupmyundong,
        ActiveStatus status,
        LocalDateTime createdTime,
        LocalDateTime modifiedTime
) {
    public static AddressResponseDto from(final Address address) {
        return new AddressResponseDto(
                address.id(),
                address.zipcode(),
                address.sido(),
                address.sigungu(),
                address.eupmyundong(),
                address.status(),
                address.createdTime(),
                address.modifiedTime()
        );
    }
}
