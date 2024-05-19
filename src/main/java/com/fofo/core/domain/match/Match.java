package com.fofo.core.domain.match;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.storage.MemberMatchEntity;

import java.time.LocalDateTime;

public record Match(
    Long id,
    Long manId,
    Long womanId,
    MatchAgreement manAgreement,
    MatchAgreement womanAgreement,
    MatchingStatus matchingStatus,
    ActiveStatus status,
    LocalDateTime createdTime,
    LocalDateTime modifiedTime
){
    public static Match from(MemberMatchEntity matchEntity){
        return new Match(
                matchEntity.getId(),
                matchEntity.getManMemberId(),
                matchEntity.getWomanMemberId(),
                matchEntity.getManAgreement(),
                matchEntity.getWomanAgreement(),
                matchEntity.getMatchingStatus(),
                matchEntity.getStatus(),
                matchEntity.getCreatedTime(),
                matchEntity.getUpdatedTime()
        );
    }
}
