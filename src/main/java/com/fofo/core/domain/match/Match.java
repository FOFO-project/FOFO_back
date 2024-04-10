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
        Member man,
        Member woman,
        MatchingStatus matchingStatus,
        ActiveStatus status,
        LocalDateTime createdTime,
        LocalDateTime modifiedTime
) {
    public static Match of(
            final Member man,
            final Member woman,
            final MatchingStatus matchingStatus,
            final ActiveStatus status
    ) {
        return new Match(
                null,
                man,
                woman,
                matchingStatus,
                status,
                null,
                null
        );
    }

    public MemberMatchEntity toEntity() {
        return MemberMatchEntity.of(
                man.id(),
                woman.id(),
                matchingStatus,
                status
        );
    }

}
