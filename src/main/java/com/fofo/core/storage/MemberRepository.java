package com.fofo.core.storage;

import com.fofo.core.domain.ActiveStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByKakaoId(String kakaoId);

    Optional<MemberEntity> findByIdAndStatusNot(Long id, ActiveStatus status);

    Page<MemberEntity> findAllByStatusNot(ActiveStatus status, Pageable pageable);
}
