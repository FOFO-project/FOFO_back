package com.fofo.core.storage;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.member.MatchableYn;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>, MemberCustomRepository {

    Optional<MemberEntity> findByKakaoIdAndStatusNot(String kakaoId, ActiveStatus status);

    Optional<MemberEntity> findByIdAndStatusNot(Long id, ActiveStatus status);

    List<MemberEntity> findAllByMatchableYnAndStatusNot(MatchableYn matchableYn, ActiveStatus status, Pageable pageable);
}
