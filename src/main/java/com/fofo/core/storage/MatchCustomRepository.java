package com.fofo.core.storage;

import com.querydsl.core.Tuple;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MatchCustomRepository {
    List<MemberEntity> findMatchPossibleMembers();
    Pair<List<Tuple>, Long> findMatchResultList(Pageable pageable);
}
