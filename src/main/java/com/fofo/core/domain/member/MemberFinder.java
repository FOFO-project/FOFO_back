package com.fofo.core.domain.member;

import com.fofo.core.storage.MemberEntity;
import com.fofo.core.storage.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberFinder {

    final private MemberRepository memberRepository;

    public MemberEntity findMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow();
    }
}
