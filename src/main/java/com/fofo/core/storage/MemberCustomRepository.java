package com.fofo.core.storage;

import com.fofo.core.domain.member.FindMember;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberCustomRepository {

    Tuple findMemberById(long memberId);

    List<Tuple> findMembersWithCondition(FindMember findMember, Pageable pageable);

}
