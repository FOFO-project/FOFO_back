package com.fofo.core.domain.match;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum MatchingStatus {
    MATCHING_PENDING("10"),
    MATCHING_PROGRESSING("20"),
    MATCHING_COMPLETED("30"),
    UNDEFINED("-1")
    ;

    private final String codeValue;

    public String codeValue() {
        return codeValue;
    }

    public static MatchingStatus enumOfCode(final String codeValue) {
        return Arrays.stream(MatchingStatus.values())
                .filter(v -> v.codeValue().equals(codeValue))
                .findAny()
                .orElse(UNDEFINED);
    }
}
