package com.fofo.core.domain.member;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fofo.core.support.error.CoreApiException;

import java.util.Arrays;

import static com.fofo.core.support.error.CoreErrorType.ENUM_MAPPING_ERROR;

public enum Mbti {
    INTJ,
    INTP,
    ENTJ,
    ENTP,
    INFJ,
    INFP,
    ENFJ,
    ENFP,
    ISTJ,
    ISFJ,
    ESTJ,
    ESFJ,
    ISTP,
    ISFP,
    ESTP,
    ESFP
    ;

    @JsonCreator
    public static Mbti enumOfCode(final String value) {
        return Arrays.stream(Mbti.values())
                .filter(v -> v.name().equals(value.toUpperCase()))
                .findAny()
                .orElseThrow(() -> new CoreApiException(ENUM_MAPPING_ERROR));
    }
}
