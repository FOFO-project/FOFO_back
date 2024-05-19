package com.fofo.core.domain.match;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.member.MatchableYn;
import com.fofo.core.storage.MatchRepository;
import com.fofo.core.storage.MemberEntity;
import com.fofo.core.storage.MemberMatchEntity;
import com.fofo.core.storage.MemberRepository;
import com.fofo.core.support.error.CoreApiException;
import com.fofo.core.support.error.CoreErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.fofo.core.support.error.CoreErrorType.MATCHABLE_MEMBER_NOT_FOUND;
import static com.fofo.core.support.error.CoreErrorType.MEMBER_NOT_FOUND_ERROR;

@Component
@RequiredArgsConstructor
public class MatchAppender {
    private final MatchRepository matchRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void appendMatches(final List<MatchWithMember> matchList) {
        matchRepository.saveAll(matchList.stream()
                .map(MatchWithMember::toEntity)
                .toList()
        );
        matchList.forEach(match -> {
            MemberEntity manMember = memberRepository.findByIdAndStatusNot(match.man().id(), ActiveStatus.DELETED)
                    .orElseThrow(() -> new CoreApiException(MEMBER_NOT_FOUND_ERROR));
            MemberEntity womanMember = memberRepository.findByIdAndStatusNot(match.woman().id(), ActiveStatus.DELETED)
                    .orElseThrow(() -> new CoreApiException(MEMBER_NOT_FOUND_ERROR));

            manMember.setMatchableYn(MatchableYn.N);
            womanMember.setMatchableYn(MatchableYn.N);
        });

    }

    @Transactional
    public void appendMatch(final Long manMemberId, final Long womanMemberId) {
        MemberEntity manMemberEntity = memberRepository.findByIdAndStatusNot(manMemberId, ActiveStatus.DELETED)
                .orElseThrow(() -> new CoreApiException(MEMBER_NOT_FOUND_ERROR));
        MemberEntity womanMemberEntity = memberRepository.findByIdAndStatusNot(womanMemberId, ActiveStatus.DELETED)
                .orElseThrow(() -> new CoreApiException(MEMBER_NOT_FOUND_ERROR));
        if(manMemberEntity.getMatchableYn() == MatchableYn.N || womanMemberEntity.getMatchableYn() == MatchableYn.N){
            throw new CoreApiException(MATCHABLE_MEMBER_NOT_FOUND);
        }
        if(manMemberEntity.getGender().equals(womanMemberEntity.getGender())){
            throw new CoreApiException(CoreErrorType.MATCH_SAME_GENDER_ERROR);
        }

        manMemberEntity.setMatchableYn(MatchableYn.N);
        womanMemberEntity.setMatchableYn(MatchableYn.N);

        matchRepository.save(MemberMatchEntity.of(
                manMemberEntity.getId(),
                womanMemberEntity.getId(),
                MatchAgreement.UNDEFINED,
                MatchAgreement.UNDEFINED,
                MatchingStatus.MATCHING_PENDING,
                ActiveStatus.CREATED
        ));
    }
}
