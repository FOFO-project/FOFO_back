package com.fofo.core.domain.match;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.member.MatchableYn;
import com.fofo.core.storage.MatchRepository;
import com.fofo.core.storage.MemberEntity;
import com.fofo.core.storage.MemberMatchEntity;
import com.fofo.core.support.error.CoreApiException;
import com.fofo.core.support.error.CoreErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MatchAppender {
    private final MatchRepository matchRepository;

    @Transactional
    public void appendMatches(final List<Match> matchList) {
        matchRepository.saveAll(matchList.stream()
                .map(Match::toEntity)
                .toList()
        );
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
        womanMemberEntity.setMatchableYn(MatchableYn.N);

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
