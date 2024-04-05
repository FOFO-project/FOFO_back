package com.fofo.core.domain.match;

import com.fofo.core.storage.MatchRepository;
import com.fofo.core.storage.MemberMatchEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MatchAppender {
    private final MatchRepository matchRepository;

    public void appendMatches(List<Match> matchList) {
        matchRepository.saveAll(Match.toEntities(matchList));
    }

    public void appendMatch(MemberMatchEntity match) {
        matchRepository.save(match);
    }
}
