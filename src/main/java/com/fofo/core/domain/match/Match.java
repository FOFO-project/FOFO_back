package com.fofo.core.domain.match;

import com.fofo.core.controller.response.MatchResponseDto.MatchResponseDto;
import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.member.Member;
import com.fofo.core.storage.MemberMatchEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record Match(
        Long id,
        Member male,
        Member female,
        MatchingStatus matchingStatus,
        ActiveStatus status,
        LocalDateTime createdTime,
        LocalDateTime modifiedTime
) {
    public static Match of(
            final Member male,
            final Member female,
            final MatchingStatus matchingStatus,
            final ActiveStatus status
    ) {
        return new Match(
                null,
                male,
                female,
                matchingStatus,
                status,
                null,
                null
        );
    }

    public MemberMatchEntity toEntity() {
        return MemberMatchEntity.of(
                male.id(),
                female.id(),
                matchingStatus,
                status
        );
    }

    public static List<MemberMatchEntity> toEntities(List<Match> matchList) {
        ArrayList<MemberMatchEntity> matchEntities = new ArrayList<>();
        matchList.forEach( match -> matchEntities.add(match.toEntity()));
        return matchEntities;
    }
}
