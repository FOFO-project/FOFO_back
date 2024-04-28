package com.fofo.core.domain.member;

import com.fofo.core.support.error.CoreApiException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static com.fofo.core.support.error.CoreErrorType.ENUM_MAPPING_ERROR;

@Getter
@RequiredArgsConstructor
public enum ApprovalStatus {

    DEPOSIT_PENDING("10"),
    DEPOSIT_COMPLETED("20"),
    APPROVED("30"),
    ;

    private final String codeValue;

    public static ApprovalStatus enumOfCode(final String codeValue) {
        return Arrays.stream(ApprovalStatus.values())
                .filter(v -> v.getCodeValue().equals(codeValue))
                .findAny()
                .orElseThrow(() -> new CoreApiException(ENUM_MAPPING_ERROR));
    }
}
