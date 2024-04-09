package com.fofo.core.domain.member;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fofo.core.support.error.CoreApiException;

import java.util.Arrays;

import static com.fofo.core.support.error.CoreErrorType.ENUM_MAPPING_ERROR;

public enum AgeRelationType {
    ORDER,
    YOUNGER,
    SAME
    ;

    @JsonCreator
    public static AgeRelationType enumOfCode(final String value) {
        return Arrays.stream(AgeRelationType.values())
                .filter(v -> v.name().equals(value.toUpperCase()))
                .findAny()
                .orElseThrow(() -> new CoreApiException(ENUM_MAPPING_ERROR));
    }
}
