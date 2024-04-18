package com.fofo.core.domain.match;

import com.fofo.core.domain.member.MemberWithAddress;

import java.time.LocalDateTime;

public record MatchResult(
        Long id,
        MemberWithAddress man,
        MemberWithAddress woman,
        MatchingStatus matchingStatus,
        LocalDateTime createdTime,
        LocalDateTime updatedTime
) {
    public static MatchResult of(
            Long id,
            MemberWithAddress manMemberWithAddress,
            MemberWithAddress womanMemberWithAddress,
            MatchingStatus matchingStatus,
            LocalDateTime createdTime,
            LocalDateTime updatedTime) {
        return new MatchResult(
                id,
                manMemberWithAddress,
                womanMemberWithAddress,
                matchingStatus,
                createdTime,
                updatedTime);
    }
}
