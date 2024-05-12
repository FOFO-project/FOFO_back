package com.fofo.core.controller.response;


import com.fofo.core.domain.match.MatchAgreement;
import com.fofo.core.domain.match.MatchResult;
import com.fofo.core.domain.match.MatchingStatus;
import com.fofo.core.domain.member.MemberWithAddress;

import java.time.LocalDateTime;

public record MatchResultResponseDto(
        Long id,
        MemberWithAddress man,
        MemberWithAddress woman,
        MatchAgreement manAgreement,
        MatchAgreement womanAgreement,
        MatchingStatus matchingStatus,
        LocalDateTime createdTime,
        LocalDateTime updatedTime
) {
   public static MatchResultResponseDto from(final MatchResult matchResult){
       return new MatchResultResponseDto(
               matchResult.id(),
               matchResult.man(),
               matchResult.woman(),
               matchResult.manAgreement(),
               matchResult.womanAgreement(),
               matchResult.matchingStatus(),
               matchResult.createdTime(),
               matchResult.updatedTime()
       );
   }
}
