package com.fofo.core.domain.match;

import com.fofo.core.domain.member.MemberWithAddress;

import java.time.LocalDateTime;

public record MatchResult(
        Long id,
        MemberWithAddress man,
        MemberWithAddress woman,
        MatchAgreement manAgreement,
        MatchAgreement womanAgreement,
        MatchingStatus matchingStatus,
        LocalDateTime createdTime,
        LocalDateTime updatedTime
) {
    public static MatchResult of(
            Long id,
            MemberWithAddress manMemberWithAddress,
            MemberWithAddress womanMemberWithAddress,
            MatchAgreement manAgreement,
            MatchAgreement womanAgreement,
            MatchingStatus matchingStatus,
            LocalDateTime createdTime,
            LocalDateTime updatedTime) {
        return new MatchResult(
                id,
                manMemberWithAddress,
                womanMemberWithAddress,
                manAgreement,
                womanAgreement,
                matchingStatus,
                createdTime,
                updatedTime);
    }
}
