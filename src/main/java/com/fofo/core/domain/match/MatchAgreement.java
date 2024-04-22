package com.fofo.core.domain.match;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fofo.core.support.error.CoreApiException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static com.fofo.core.support.error.CoreErrorType.ENUM_MAPPING_ERROR;

@Getter
@RequiredArgsConstructor
public enum MatchAgreement {
    Y(true),
    N(false)
    ;

    private final boolean codeValue;

    @JsonCreator
    public static MatchAgreement enumOfName(final String name) {
        return Arrays.stream(MatchAgreement.values())
                .filter(v -> v.name().equals(name.toUpperCase()))
                .findAny()
                .orElseThrow(() -> new CoreApiException(ENUM_MAPPING_ERROR));
    }

    public static MatchAgreement enumOfCode(final boolean codeValue) {
        return Arrays.stream(MatchAgreement.values())
                .filter(v -> v.isCodeValue() == codeValue)
                .findAny()
                .orElseThrow(() -> new CoreApiException(ENUM_MAPPING_ERROR));
    }
}
