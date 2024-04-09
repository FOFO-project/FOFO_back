package com.fofo.core.controller.response.MatchResponseDto;


import com.fofo.core.domain.match.Match;
import com.fofo.core.domain.member.Member;

public record MatchResponseDto(
        // MemberReponseDto 로 해야하는데 일단 이렇게
        Member male,
        Member female
) {
   public static MatchResponseDto of(
           final Member male,
           final Member female
   ) {
       return new MatchResponseDto(
               male,
               female
       );
   }

   public static MatchResponseDto from(final Match match){
       return new MatchResponseDto(
               match.male(),
               match.female()
       );
   }
}
