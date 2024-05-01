package com.fofo.core.storage;

import com.fofo.core.domain.ActiveStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<MemberImageEntity, Long> {

    Optional<MemberImageEntity> findByIdAndStatusNot(Long id, ActiveStatus status);

    List<MemberImageEntity> findByMemberIdAndStatusNot(Long id, ActiveStatus status);

    List<MemberImageEntity> findByMemberIdInAndStatusNot(Collection<Long> memberId, ActiveStatus status);
}
