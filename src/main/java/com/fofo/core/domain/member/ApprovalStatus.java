package com.fofo.core.domain.member;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum ApprovalStatus {

    DEPOSIT_PENDING("10"),
    DEPOSIT_COMPLETED("20"),
    APPROVED("30"),
    UNDEFINED("-1")
    ;

    private final String codeValue;

    public String codeValue() {
        return codeValue;
    }

    public static ApprovalStatus enumOfCode(final String codeValue) {
        return Arrays.stream(ApprovalStatus.values())
                .filter(v -> v.codeValue().equals(codeValue))
                .findAny()
                .orElse(UNDEFINED);
    }
}
