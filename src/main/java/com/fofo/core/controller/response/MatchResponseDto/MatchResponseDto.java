package com.fofo.core.controller.response.MatchResponseDto;


import com.fofo.core.domain.match.Match;
import com.fofo.core.domain.member.Member;

import java.util.ArrayList;
import java.util.List;

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

    public static List<MatchResponseDto> createMatchResponseDtoList(List<Match> matchList) {
        ArrayList<MatchResponseDto> matchResponseDtoList = new ArrayList<>();
        matchList.forEach(match -> matchResponseDtoList.add(
                MatchResponseDto.of(
                        // MemberResponseDto.of(match.male())
                        match.male(),
                        match.female()
                )
        ));
        return matchResponseDtoList;
    }
}
