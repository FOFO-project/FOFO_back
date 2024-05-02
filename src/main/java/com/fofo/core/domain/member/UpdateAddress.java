package com.fofo.core.domain.member;

import com.fofo.core.domain.ActiveStatus;

public record UpdateAddress(
        String sido,
        String sigungu,
        String eupmyundong,
        ActiveStatus status
) {
    public static UpdateAddress of(final String sido,
                                   final String sigungu,
                                   final String eupmyundong) {
        return new UpdateAddress(sido, sigungu, eupmyundong, ActiveStatus.UPDATED);
    }
}
