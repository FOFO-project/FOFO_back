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

import static com.fofo.core.support.error.CoreErrorType.MEMBER_NOT_FOUND_ERROR;

@Component
@RequiredArgsConstructor
public class MatchAppender {
    private final MatchRepository matchRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void appendMatches(final List<Match> matchList) {
        matchRepository.saveAll(matchList.stream()
                .map(Match::toEntity)
                .toList()
        );
        matchList.forEach(match -> {
            MemberEntity manMember = memberRepository.findByIdAndStatusNot(match.man().id(), ActiveStatus.DELETED)
                    .orElseThrow(() -> new CoreApiException(MEMBER_NOT_FOUND_ERROR));
            MemberEntity womanMember = memberRepository.findByIdAndStatusNot(match.woman().id(), ActiveStatus.DELETED)
                    .orElseThrow(() -> new CoreApiException(MEMBER_NOT_FOUND_ERROR));

            manMember.setMatchableYn(MatchableYn.Y);
            memberRepository.save(manMember);
            womanMember.setMatchableYn(MatchableYn.Y);
            memberRepository.save(womanMember);
        });

    }

    @Transactional
    public void appendMatch(final Long manMemberId, final Long womanMemberId) {
        MemberEntity manMemberEntity = matchRepository.findMatchPossibleMemberById(manMemberId)
                .orElseThrow(() -> new CoreApiException(CoreErrorType.MATCHABLE_MEMBER_NOT_FOUND));
        MemberEntity womanMemberEntity = matchRepository.findMatchPossibleMemberById(womanMemberId)
                .orElseThrow(() -> new CoreApiException(CoreErrorType.MATCHABLE_MEMBER_NOT_FOUND));
        if(manMemberEntity.getGender().equals(womanMemberEntity.getGender())){
            throw new CoreApiException(CoreErrorType.MATCH_SAME_GENDER_ERROR);
        }

        manMemberEntity.setMatchableYn(MatchableYn.N);
        memberRepository.save(manMemberEntity);
        womanMemberEntity.setMatchableYn(MatchableYn.N);
        memberRepository.save(womanMemberEntity);

        matchRepository.save(MemberMatchEntity.of(
                manMemberEntity.getId(),
                womanMemberEntity.getId(),
                MatchAgreement.N,
                MatchAgreement.N,
                MatchingStatus.MATCHING_PENDING,
                ActiveStatus.CREATED
        ));
    }
}
