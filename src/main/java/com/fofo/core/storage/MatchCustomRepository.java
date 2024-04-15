package com.fofo.core.storage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MatchCustomRepository {
    List<MemberEntity> findMatchPossibleMembers();
    Page<MatchResultDto> findMatchResultList(Pageable pageable);
}
