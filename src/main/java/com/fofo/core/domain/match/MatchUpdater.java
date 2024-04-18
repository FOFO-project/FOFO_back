package com.fofo.core.domain.match;

import com.fofo.core.domain.ActiveStatus;
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

    public void updateMatchCOMPLETED(final Long id,
                                     final Long manId,
                                     final Boolean manAgreement,
                                     final Long womanId,
                                     final Boolean womanAgreement,
                                     final MatchingStatus nextMatchingStatus) {
        if(!manAgreement){
            MemberEntity member = memberRepository.findByIdAndStatusNot(manId, ActiveStatus.DELETED)
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
        if(!womanAgreement){
            MemberEntity member = memberRepository.findByIdAndStatusNot(womanId, ActiveStatus.DELETED)
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

        MemberMatchEntity matchEntity = matchRepository.findByIdAndStatusNot(id, ActiveStatus.DELETED)
                .orElseThrow(() -> new CoreApiException(CoreErrorType.MATCH_NOT_FOUND_ERROR));
        matchEntity.setMatchingStatus(nextMatchingStatus);
        matchRepository.save(matchEntity);
    }
}
