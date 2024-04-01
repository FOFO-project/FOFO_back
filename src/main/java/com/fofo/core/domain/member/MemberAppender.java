package com.fofo.core.domain.member;

import com.fofo.core.storage.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberAppender {

    private final MemberRepository memberRepository;
}
