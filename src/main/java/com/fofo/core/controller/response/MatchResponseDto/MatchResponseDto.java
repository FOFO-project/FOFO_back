package com.fofo.core.controller.response.MatchResponseDto;


import com.fofo.core.domain.match.Match;
import com.fofo.core.domain.member.Member;

public record MatchResponseDto(
        // MemberReponseDto 로 해야하는데 일단 이렇게
        Member man,
        Member woman
) {
   public static MatchResponseDto of(
           final Member man,
           final Member woman
   ) {
       return new MatchResponseDto(
               man,
               woman
       );
   }

   public static MatchResponseDto from(final Match match){
       return new MatchResponseDto(
               match.man(),
               match.woman()
       );
   }
}
