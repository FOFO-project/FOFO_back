package com.fofo.core.domain;

import com.fofo.core.support.error.CoreApiException;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static com.fofo.core.support.error.CoreErrorType.ENUM_MAPPING_ERROR;

@RequiredArgsConstructor
public enum ActiveStatus {
    CREATED("C"),
    UPDATED("U"),
    DELETED("D")
    ;

    private final String codeValue;

    public String codeValue() {
        return codeValue;
    }

    public static ActiveStatus enumOfCode(final String codeValue) {
        return Arrays.stream(ActiveStatus.values())
                .filter(v -> v.codeValue().equals(codeValue))
                .findAny()
                .orElseThrow(() -> new CoreApiException(ENUM_MAPPING_ERROR));
    }

}
