package com.fofo.core.storage;

import com.fofo.core.domain.match.MatchingStatus;
import com.querydsl.core.Tuple;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MatchCustomRepository {
    Optional<MemberEntity> findMatchPossibleMemberById(Long id);
    Pair<List<Tuple>, Long> findMatchResultList(Pageable pageable, MatchingStatus matchingStatus);
}
