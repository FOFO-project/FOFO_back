package com.fofo.core.storage;

import com.fofo.core.domain.ActiveStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    Optional<AddressEntity> findByIdAndStatusNot(Long id, ActiveStatus status);
}
