package com.fofo.core.controller.response;


import com.fofo.core.domain.match.Match;
import com.fofo.core.domain.member.Member;

public record MatchResultResponseDto(
        // 멤버 조회 Dto 아직 없어서 임시로 Member 도메인 사용
//        MemberReponseDto man,
//        MemberResponseDto woman
        Member man,
        Member woman
) {
   public static MatchResultResponseDto of(
           final Member man,
           final Member woman
   ) {
       return new MatchResultResponseDto(
               man,
               woman
       );
   }

   public static MatchResultResponseDto from(final Match match){
//       MemberReponseDto.from(match.man())
//       MemberReponseDto.from(match.woman())
       return new MatchResultResponseDto(
               match.man(),
               match.woman()
       );
   }
}
