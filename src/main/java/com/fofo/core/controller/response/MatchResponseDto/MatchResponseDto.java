package com.fofo.core.controller.response.MatchResponseDto;


import com.fofo.core.domain.match.Match;
import com.fofo.core.domain.member.Member;

public record MatchResponseDto(
        // 멤버 조회 Dto 아직 없어서 임시로 Member 도메인 사용
//        MemberReponseDto man,
//        MemberResponseDto woman
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
//       MemberReponseDto.from(match.man())
//       MemberReponseDto.from(match.woman())
       return new MatchResponseDto(
               match.man(),
               match.woman()
       );
   }
}
