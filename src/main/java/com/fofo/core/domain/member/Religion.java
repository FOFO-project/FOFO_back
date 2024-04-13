package com.fofo.core.domain.member;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fofo.core.support.error.CoreApiException;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static com.fofo.core.support.error.CoreErrorType.ENUM_MAPPING_ERROR;

@RequiredArgsConstructor
public enum Religion {
    NON_RELIGIOUS("0"),
    CHRISTIANITY("1"),
    CATHOLIC("2"),
    BUDDHISM("3"),
    WON_BUDDHISM("4"),
    OTHERS("-1");

    private final String codeValue;

    public String codeValue() {
        return codeValue;
    }

    @JsonCreator
    public static Religion enumOfName(final String name) {
        return Arrays.stream(Religion.values())
                .filter(v -> v.name().equals(name))
                .findAny()
                .orElseThrow(() -> new CoreApiException(ENUM_MAPPING_ERROR));
    }

    public static Religion enumOfCode(final String codeValue) {
        return Arrays.stream(Religion.values())
                .filter(v -> v.codeValue().equals(codeValue))
                .findAny()
                .orElseThrow(() -> new CoreApiException(ENUM_MAPPING_ERROR));
    }
}
