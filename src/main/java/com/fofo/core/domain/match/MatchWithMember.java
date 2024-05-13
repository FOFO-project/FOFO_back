package com.fofo.core.domain.match;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.member.Member;
import com.fofo.core.storage.MemberMatchEntity;

import java.time.LocalDateTime;

public record MatchWithMember(
        Long id,
        Member man,
        Member woman,
        MatchAgreement manAgreement,
        MatchAgreement womanAgreement,
        MatchingStatus matchingStatus,
        ActiveStatus status,
        LocalDateTime createdTime,
        LocalDateTime modifiedTime
) {
    public static MatchWithMember of(
            final Member man,
            final Member woman,
            final MatchAgreement manAgreement,
            final MatchAgreement womanAgreement,
            final MatchingStatus matchingStatus,
            final ActiveStatus status
    ) {
        return new MatchWithMember(
                null,
                man,
                woman,
                manAgreement,
                womanAgreement,
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
                manAgreement,
                womanAgreement,
                matchingStatus,
                status
        );
    }

}
