package com.fofo.core.domain.member;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.storage.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberFinder {

    private final MemberRepository memberRepository;

    public List<Member> findMatchableMembers() {
        return memberRepository.findAllByMatchableYnAndStatusNot(MatchableYn.Y,
                        ActiveStatus.DELETED,
                        Pageable.unpaged(Sort.by(Sort.Direction.ASC, "depositDate"))).stream()
                .map(Member::from)
                .toList();
    }
}
