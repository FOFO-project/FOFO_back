package com.fofo.core.domain.member;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.storage.MemberRepository;
import com.fofo.core.support.error.CoreApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import static com.fofo.core.support.error.CoreErrorType.MEMBER_NOT_FOUND_ERROR;

@Component
@RequiredArgsConstructor
public class MemberFinder {

    private final MemberRepository memberRepository;

    public Member find(final long memberId) {
        return memberRepository.findByIdAndStatusNot(memberId, ActiveStatus.DELETED).stream()
                .map(Member::from)
                .findAny()
                .orElseThrow(() -> new CoreApiException(MEMBER_NOT_FOUND_ERROR));
    }

    public Page<Member> findAll(final FindMember findMember, final Pageable pageable) {
        // TODO. 전체 조회 필터는 추후 구현 예정
        return memberRepository.findAllByStatusNot(ActiveStatus.DELETED, pageable).map(Member::from);
    }
}
