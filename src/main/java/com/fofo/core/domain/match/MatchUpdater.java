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
        matchRepository.save(matchEntity);
    }

    public void updateMatchCompleted(final Long id,
                                     final Long manId,
                                     final MatchAgreement manAgreement,
                                     final Long womanId,
                                     final MatchAgreement womanAgreement,
                                     final MatchingStatus nextMatchingStatus) {
        if (MatchAgreement.N == manAgreement) {
            updateMemberPassCount(manId);
        }
        if (MatchAgreement.N == womanAgreement) {
            updateMemberPassCount(womanId);
        }

        MemberMatchEntity matchEntity = matchRepository.findByIdAndStatusNot(id, ActiveStatus.DELETED)
                .orElseThrow(() -> new CoreApiException(CoreErrorType.MATCH_NOT_FOUND_ERROR));
        matchEntity.setMatchingStatus(nextMatchingStatus);
        matchEntity.setManAgreement(manAgreement);
        matchEntity.setWomanAgreement(womanAgreement);
        matchRepository.save(matchEntity);

        if (isMatchContinuePossible(manAgreement, womanAgreement)) {
            MemberEntity manMember = memberRepository.findByIdAndStatusNot(manId, ActiveStatus.DELETED)
                    .orElseThrow(() -> new CoreApiException(MEMBER_NOT_FOUND_ERROR));
            MemberEntity womanMember = memberRepository.findByIdAndStatusNot(womanId, ActiveStatus.DELETED)
                    .orElseThrow(() -> new CoreApiException(MEMBER_NOT_FOUND_ERROR));

            manMember.setMatchableYn(MatchableYn.Y);
            memberRepository.save(manMember);
            womanMember.setMatchableYn(MatchableYn.Y);
            memberRepository.save(womanMember);
        }
    }

    private boolean isMatchContinuePossible(final MatchAgreement manAgreement, final MatchAgreement womanAgreement) {
        return !(MatchAgreement.Y == manAgreement && MatchAgreement.Y == womanAgreement);
    }

    private void updateMemberPassCount(final Long memberId) {
        MemberEntity member = memberRepository.findByIdAndStatusNot(memberId, ActiveStatus.DELETED)
                .orElseThrow(() -> new CoreApiException(MEMBER_NOT_FOUND_ERROR));
        int nextChance = member.getChance();
        int nextPassCount = member.getPassCount() - 1;
        if (nextPassCount == 0){
            if (member.getChance() == 1){
                nextChance = 0;
            } else {
                nextChance--;
                nextPassCount = 5;
            }
        }
        member.setChance(nextChance);
        member.setPassCount(nextPassCount);
        memberRepository.save(member);
    }
}
