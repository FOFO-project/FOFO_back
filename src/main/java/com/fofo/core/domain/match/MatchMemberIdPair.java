package com.fofo.core.domain.match;

public record MatchMemberIdPair(Long manId, Long womanId) {
    public static MatchMemberIdPair of(Long manId, Long womanId){
        return new MatchMemberIdPair(manId, womanId);
    }
}
