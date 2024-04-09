package com.fofo.core.domain.match;

import com.fofo.core.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MatchManager {
    public List<Match> matchByFilteringCondition(final List<Member> matchPossibleMembers) {
        return null;
    }

    public MatchingStatus getNextMatchingStatus(final MatchingStatus matchingStatus) {
        return switch (matchingStatus){
            case MATCHING_PENDING -> MatchingStatus.MATCHING_PROGRESSING;
            case MATCHING_PROGRESSING, MATCHING_COMPLETED -> MatchingStatus.MATCHING_COMPLETED;
        };
    }
}
