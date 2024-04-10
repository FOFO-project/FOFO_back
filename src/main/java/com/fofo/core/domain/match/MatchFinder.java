package com.fofo.core.domain.match;

import com.fofo.core.domain.member.Member;
import com.fofo.core.storage.MatchRepository;
import com.fofo.core.storage.MemberEntity;
import com.fofo.core.storage.MemberRepository;
import com.fofo.core.support.error.CoreApiException;
import com.fofo.core.support.error.CoreErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MatchFinder {

    private final MatchRepository matchRepository;
    private final MemberRepository memberRepository;

    public List<Member> findMatchPossibleMembers() {
        return null;
    }

    public Page<Match> findMatches(final int page, final int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return matchRepository.selectMatchResultList(pageRequest);
    }

    public MemberEntity findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new CoreApiException(
                        CoreErrorType.MEMBER_NOT_FOUND_ERROR)
                );
    }
}
