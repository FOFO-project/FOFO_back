package com.fofo.core.storage;

import com.fofo.core.domain.match.MatchingStatus;
import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;


public record MatchResultDto(
    Long id,
    MatchResultMemberDto man,
    MatchResultMemberDto woman,
    MatchingStatus matchingStatus,
    LocalDateTime createdTime,
    LocalDateTime modifiedTime
){
    @QueryProjection
    public MatchResultDto(Long id, MatchResultMemberDto man, MatchResultMemberDto woman, MatchingStatus matchingStatus, LocalDateTime createdTime, LocalDateTime modifiedTime) {
        this.id = id;
        this.man = man;
        this.woman = woman;
        this.matchingStatus = matchingStatus;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }

    public static QMatchResultDto from(QMemberMatchEntity match, QMemberEntity manMember, QMemberEntity womanMember, QAddressEntity manAddress, QAddressEntity womanAddress) {
        return new QMatchResultDto(
                match.id,
                MatchResultMemberDto.from(manMember, manAddress),
                MatchResultMemberDto.from(womanMember, womanAddress),
                match.matchingStatus,
                match.createdTime,
                match.updatedTime
                );
    }
}
