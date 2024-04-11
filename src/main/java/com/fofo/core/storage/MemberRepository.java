package com.fofo.core.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByKakaoId(String kakaoId);

    @Query("select m from MemberEntity m " +
            "where m.approvalStatus = '30' " +
            "and m.status != 'DELETED' " +
            "order by m.depositDate DESC ")
    List<MemberEntity> findMatchPossibleMembers();
}
