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

import static com.fofo.core.support.error.CoreErrorType.MEMBER_NOT_FOUND_ERROR;

@Component
@RequiredArgsConstructor
public class MatchUpdater {

    private final MatchRepository matchRepository;
    private final MemberRepository memberRepository;

    public void updateMatchProgressing(final Long id, final MatchingStatus nextMatchingStatus) {
        MemberMatchEntity matchEntity = matchRepository.findByIdAndStatusNot(id, ActiveStatus.DELETED)
                .orElseThrow(() -> new CoreApiException(CoreErrorType.MATCH_NOT_FOUND_ERROR));
        matchEntity.setMatchingStatus(nextMatchingStatus);
        matchEntity.setStatus(ActiveStatus.UPDATED);
    }

    public void updateMatchCompleted(final Long id,
                                     final Long manId,
                                     final MatchAgreement manAgreement,
                                     final Long womanId,
                                     final MatchAgreement womanAgreement,
                                     final MatchingStatus nextMatchingStatus) {
        updateMatchingMember(manId, manAgreement, womanId, womanAgreement);

        MemberMatchEntity matchEntity = matchRepository.findByIdAndStatusNot(id, ActiveStatus.DELETED)
                .orElseThrow(() -> new CoreApiException(CoreErrorType.MATCH_NOT_FOUND_ERROR));
        matchEntity.setMatchingStatus(nextMatchingStatus);
        matchEntity.setManAgreement(manAgreement);
        matchEntity.setWomanAgreement(womanAgreement);
        matchEntity.setStatus(ActiveStatus.UPDATED);
    }

    private void updateMatchingMember(final Long manId, final MatchAgreement manAgreement, final Long womanId, final MatchAgreement womanAgreement) {
        MemberEntity manMember = memberRepository.findByIdAndStatusNot(manId, ActiveStatus.DELETED)
                .orElseThrow(() -> new CoreApiException(MEMBER_NOT_FOUND_ERROR));
        MemberEntity womanMember = memberRepository.findByIdAndStatusNot(womanId, ActiveStatus.DELETED)
                .orElseThrow(() -> new CoreApiException(MEMBER_NOT_FOUND_ERROR));

        if (MatchAgreement.Y == manAgreement && MatchAgreement.Y == womanAgreement) {  // 둘다 동의한 경우
            manMember.decreaseChance();
            womanMember.decreaseChance();
        } else if (MatchAgreement.N == manAgreement && MatchAgreement.Y == womanAgreement) { // 남자만 거절한 경우
            manMember.usePassCount();
            womanMember.setMatchableYn(MatchableYn.Y);
        } else if (MatchAgreement.Y == manAgreement && MatchAgreement.N == womanAgreement) { // 여자만 거절한 경우
            womanMember.usePassCount();
            manMember.setMatchableYn(MatchableYn.Y);
        } else { // 둘다 거절한 경우
            manMember.usePassCount();
            womanMember.usePassCount();
        }

        manMember.setStatus(ActiveStatus.UPDATED);
        womanMember.setStatus(ActiveStatus.UPDATED);
    }

    public void updateMatchAgreements(final Long id, final MatchAgreement manAgreement, final MatchAgreement womanAgreement) {
        MemberMatchEntity findMatch = matchRepository.findByIdAndStatusNot(id, ActiveStatus.DELETED)
                .orElseThrow(() -> new CoreApiException(CoreErrorType.MATCH_NOT_FOUND_ERROR));

        findMatch.setManAgreement(manAgreement);
        findMatch.setWomanAgreement(womanAgreement);
        findMatch.setStatus(ActiveStatus.UPDATED);
    }

}
