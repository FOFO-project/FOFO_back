package com.fofo.core.domain.match;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.member.MatchableYn;
import com.fofo.core.storage.MatchRepository;
import com.fofo.core.storage.MemberEntity;
import com.fofo.core.storage.MemberMatchEntity;
import com.fofo.core.storage.MemberRepository;
import com.fofo.core.support.error.CoreApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.fofo.core.support.error.CoreErrorType.MATCH_NOT_CANCELABLE_ERROR;
import static com.fofo.core.support.error.CoreErrorType.MEMBER_NOT_FOUND_ERROR;

@Component
@RequiredArgsConstructor
public class MatchRemover {

    private final MatchRepository matchRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void removeMatch(final List<Long> matchIdList) {
        List<MemberMatchEntity> matchEntityList = matchRepository.findAllInIdsByStatusNot(matchIdList, ActiveStatus.DELETED);
        matchEntityList.forEach(this::updateCancel);
        matchRepository.saveAll(matchEntityList);
    }

    private void updateCancel(MemberMatchEntity matchEntity) {
        if(!MatchingStatus.MATCHING_PENDING.equals(matchEntity.getMatchingStatus())){
            throw new CoreApiException(MATCH_NOT_CANCELABLE_ERROR);
        }

        matchEntity.setStatus(ActiveStatus.DELETED);

        MemberEntity manMember = memberRepository.findByIdAndStatusNot(matchEntity.getManMemberId(), ActiveStatus.DELETED)
                .orElseThrow(() -> new CoreApiException(MEMBER_NOT_FOUND_ERROR));
        MemberEntity womanMember = memberRepository.findByIdAndStatusNot(matchEntity.getWomanMemberId(), ActiveStatus.DELETED)
                .orElseThrow(() -> new CoreApiException(MEMBER_NOT_FOUND_ERROR));

        manMember.setMatchableYn(MatchableYn.Y);
        memberRepository.save(manMember);
        womanMember.setMatchableYn(MatchableYn.Y);
        memberRepository.save(womanMember);
    }

}
