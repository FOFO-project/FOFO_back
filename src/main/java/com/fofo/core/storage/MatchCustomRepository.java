package com.fofo.core.storage;

import com.fofo.core.domain.match.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MatchCustomRepository {
    long deleteMatchesBy(List<Long> matchIdList, String hold);
    Page<Match> selectMatchResultList(Pageable pageable);
}
