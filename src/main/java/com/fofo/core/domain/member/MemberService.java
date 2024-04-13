package com.fofo.core.domain.member;

import com.fofo.core.domain.support.SortType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberAppender memberAppender;
    private final MemberFinder memberFinder;

    public long append(final Member member, final Address address) {
        return memberAppender.append(member, address);
    }

    public Member find(final long memberId) {
        return memberFinder.find(memberId);
    }

    public Page<Member> findAll(final FindMember findMember, final int pageNumber, final int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, SortType.DEFAULT_SORT_TYPE);
        return memberFinder.findAll(findMember, pageRequest);
    }

}
