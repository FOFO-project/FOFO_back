package com.fofo.core.domain.member;

import com.fofo.core.domain.ActiveStatus;

public record UpdateAddress(
        String sido,
        String sigungu,
        ActiveStatus status
) {
    public static UpdateAddress of(final String sido,
                                   final String sigungu) {
        return new UpdateAddress(sido, sigungu, ActiveStatus.UPDATED);
    }
}
