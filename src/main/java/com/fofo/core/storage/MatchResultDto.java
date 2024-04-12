package com.fofo.core.storage;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.match.MatchingStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MatchResultDto{
    private Long id;
    private MatchResultMemberDto man;
    private MatchResultMemberDto woman;
    private MatchingStatus matchingStatus;
    private ActiveStatus status;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

    @QueryProjection
    public MatchResultDto(Long id, MatchResultMemberDto man, MatchResultMemberDto woman, MatchingStatus matchingStatus, ActiveStatus status, LocalDateTime createdTime, LocalDateTime modifiedTime) {
        this.id = id;
        this.man = man;
        this.woman = woman;
        this.matchingStatus = matchingStatus;
        this.status = status;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }

    public static QMatchResultDto getQMatchResultDto(QMemberMatchEntity match, QMemberEntity manMember, QMemberEntity womanMember) {
        return new QMatchResultDto(
                match.id,
                MatchResultMemberDto.getQMatchMemberResultDto(manMember),
                MatchResultMemberDto.getQMatchMemberResultDto(womanMember),
                match.matchingStatus,
                match.status,
                match.createdTime,
                match.updatedTime
                );
    }
}
