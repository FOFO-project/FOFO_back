package com.fofo.core.domain.member;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fofo.core.support.error.CoreApiException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static com.fofo.core.support.error.CoreErrorType.ENUM_MAPPING_ERROR;

@Getter
@RequiredArgsConstructor
public enum AgeRelationType {
    OLDER("1"),
    YOUNGER("-1"),
    SAME("0")
    ;

    private final String codeValue;

    @JsonCreator
    public static AgeRelationType enumOfName(final String name) {
        return Arrays.stream(AgeRelationType.values())
                .filter(v -> v.name().equals(name.toUpperCase()))
                .findAny()
                .orElseThrow(() -> new CoreApiException(ENUM_MAPPING_ERROR));
    }

    public static AgeRelationType enumOfCode(final String codeValue) {
        return Arrays.stream(AgeRelationType.values())
                .filter(v -> v.getCodeValue().equals(codeValue))
                .findAny()
                .orElseThrow(() -> new CoreApiException(ENUM_MAPPING_ERROR));
    }
}
