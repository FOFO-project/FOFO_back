package com.fofo.core.controller.response;

import java.util.List;

public record MatchResponseDto(List<Long> unmatchedMemberIdList) {
    public static MatchResponseDto of(final List<Long> unmatchedMemberIdList){
        return new MatchResponseDto(unmatchedMemberIdList);
    }
}
