package com.fofo.core.domain.member;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fofo.core.support.error.CoreApiException;

import java.util.Arrays;

import static com.fofo.core.support.error.CoreErrorType.ENUM_MAPPING_ERROR;

public enum Religion {
    CHRISTIANITY,
    CATHOLIC,
    BUDDHISM,
    WON_BUDDHISM,
    NON_RELIGIOUS,
    OTHERS;

    @JsonCreator
    public static Religion enumOfCode(final String value) {
        return Arrays.stream(Religion.values())
                .filter(v -> v.name().equals(value.toUpperCase()))
                .findAny()
                .orElseThrow(() -> new CoreApiException(ENUM_MAPPING_ERROR));
    }
}
