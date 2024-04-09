package com.fofo.core.domain.match;

import com.fofo.core.storage.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MatchRemover {

    private final MatchRepository matchRepository;

    public void removeMatch(final List<Long> matchIdList) {
        matchRepository.deleteMatchesBy(matchIdList);
    }
}
