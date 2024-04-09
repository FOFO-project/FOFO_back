package com.fofo.core.domain.match;

import com.fofo.core.storage.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MatchUpdater {

    private final MatchRepository matchRepository;

    public void updateMatchStatus(final List<Long> matchIdList, final MatchingStatus matchingStatus) {
        matchRepository.updateMatchStatus(matchIdList, matchingStatus);
    }
}
