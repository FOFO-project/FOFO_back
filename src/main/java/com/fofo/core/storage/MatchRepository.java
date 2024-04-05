package com.fofo.core.storage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<MemberMatchEntity, Long>, MatchCustomRepository {
}
