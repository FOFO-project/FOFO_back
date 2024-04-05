package com.fofo.core.storage;

import com.fofo.core.domain.match.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MatchRepository extends JpaRepository<Match, Long>, MatchCustomRepository {
}
