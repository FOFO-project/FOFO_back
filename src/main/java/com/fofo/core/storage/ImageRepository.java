package com.fofo.core.storage;

import com.fofo.core.domain.ActiveStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<MemberImageEntity, Long> {

    Optional<MemberImageEntity> findByIdAndStatusNot(Long id, ActiveStatus status);
}
