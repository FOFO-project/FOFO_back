package com.fofo.core.domain.match;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fofo.core.support.error.CoreApiException;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static com.fofo.core.support.error.CoreErrorType.ENUM_MAPPING_ERROR;

@RequiredArgsConstructor
public enum MatchingStatus {
    MATCHING_PENDING("10"),
    MATCHING_PROGRESSING("20"),
    MATCHING_COMPLETED("30"),
    ;

    private final String codeValue;

    public String codeValue() {
        return codeValue;
    }

    @JsonCreator
    public static MatchingStatus enumOfCode(final String codeValue) {
        return Arrays.stream(MatchingStatus.values())
                .filter(v -> v.codeValue().equals(codeValue))
                .findAny()
                .orElseThrow(() -> new CoreApiException(ENUM_MAPPING_ERROR));
    }

}
