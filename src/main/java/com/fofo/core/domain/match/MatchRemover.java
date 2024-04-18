package com.fofo.core.domain.match;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.storage.MatchRepository;
import com.fofo.core.storage.MemberMatchEntity;
import com.fofo.core.support.error.CoreApiException;
import com.fofo.core.support.error.CoreErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MatchRemover {

    private final MatchRepository matchRepository;

    @Transactional
    public void removeMatch(final List<Long> matchIdList) {
        List<MemberMatchEntity> matchEntityList = matchRepository.findAllInIdsByStatusNot(matchIdList, ActiveStatus.DELETED);
        matchEntityList.forEach(this::updateCancel);
        matchRepository.saveAll(matchEntityList);
    }

    private void updateCancel(MemberMatchEntity matchEntity) {
        if(!MatchingStatus.MATCHING_PENDING.equals(matchEntity.getMatchingStatus())){
            throw new CoreApiException(CoreErrorType.MATCH_UNCANCELABLE_ERROR);
        } else {
            matchEntity.setStatus(ActiveStatus.DELETED);
        }
    }

}
