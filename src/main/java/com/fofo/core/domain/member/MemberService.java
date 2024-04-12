package com.fofo.core.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberAppender memberAppender;

    public long append(final Member member, final Address address) {
        return memberAppender.append(member, address);
    }

}
