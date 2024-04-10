package com.fofo.core.domain.match;

import com.fofo.core.storage.MatchRepository;
import com.fofo.core.support.error.CoreApiException;
import com.fofo.core.support.error.CoreErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MatchRemover {

    private final MatchRepository matchRepository;

    public void removeMatch(final List<Long> matchIdList) {
        if(!matchRepository.findUnCancelableMatches(matchIdList).isEmpty()){
            throw new CoreApiException(CoreErrorType.MATCH_UNCANCELABLE_ERROR);
        };
        matchRepository.deleteMatchesBy(matchIdList);
    }
}
