package com.fofo.core.domain.match;

import com.fofo.core.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MatchManager {
    public List<Match> matchByFilteringCondition(List<Member> matchPossibleMembers) {
        return null;
    }
}
