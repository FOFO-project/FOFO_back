package com.fofo.core.domain.match;

import com.fofo.core.domain.member.Form;

import java.time.LocalDateTime;

public record MatchResult(
        Long id,
        Form manForm,
        Form womanForm,
        MatchAgreement manAgreement,
        MatchAgreement womanAgreement,
        MatchingStatus matchingStatus,
        LocalDateTime createdTime,
        LocalDateTime updatedTime
) {
    public static MatchResult of(
            Long id,
            Form manForm,
            Form womanForm,
            MatchAgreement manAgreement,
            MatchAgreement womanAgreement,
            MatchingStatus matchingStatus,
            LocalDateTime createdTime,
            LocalDateTime updatedTime) {
        return new MatchResult(
                id,
                manForm,
                womanForm,
                manAgreement,
                womanAgreement,
                matchingStatus,
                createdTime,
                updatedTime);
    }
}
