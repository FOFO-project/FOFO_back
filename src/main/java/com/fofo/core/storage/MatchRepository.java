package com.fofo.core.storage;

import com.fofo.core.domain.ActiveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<MemberMatchEntity, Long>, MatchCustomRepository {
    Optional<MemberMatchEntity> findByIdAndStatusNot(Long id, ActiveStatus status);
    @Query(value = "select match " +
            "from MemberMatchEntity match " +
            "where match.id in :ids " +
            "and match.status != :status ")
    List<MemberMatchEntity> findAllInIdsByStatusNot(@Param("ids") List<Long> ids, @Param("status") ActiveStatus status);
}
