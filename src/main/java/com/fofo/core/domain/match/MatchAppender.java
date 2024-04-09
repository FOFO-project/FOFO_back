package com.fofo.core.domain.match;

import com.fofo.core.storage.MatchRepository;
import com.fofo.core.storage.MemberMatchEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MatchAppender {
    private final MatchRepository matchRepository;

    public void appendMatches(final List<Match> matchList) {
        matchRepository.saveAll(matchList.stream()
                .map(Match::toEntity)
                .toList()
        );
    }

    public void appendMatch(final MemberMatchEntity match) {
        matchRepository.save(match);
    }
}
