package com.fofo.core.domain.match;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.member.Address;
import com.fofo.core.domain.member.AgeRelationType;
import com.fofo.core.domain.member.ApprovalStatus;
import com.fofo.core.domain.member.Gender;
import com.fofo.core.domain.member.GeoPoint;
import com.fofo.core.domain.member.Mbti;
import com.fofo.core.domain.member.Member;
import com.fofo.core.domain.member.MemberService;
import com.fofo.core.domain.member.Religion;
import com.fofo.core.storage.MatchRepository;
import com.fofo.core.storage.MatchResultDto;
import com.fofo.core.storage.MemberMatchEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
class MatchServiceTest {

    @Autowired MatchService matchService;
    @Autowired MemberService memberService;
    @Autowired
    MatchRepository matchRepository;

    @Test
    void autoMatch() {
        //given
        for(int i=1; i < 6; i++){
            Member member = getMember(i, Gender.MAN);
            Address address = getAddress();
            memberService.append(member, address);
        }
        for(int i=6; i < 11; i++){
            Member member = getMember(i, Gender.WOMAN);
            Address address = getAddress();
            memberService.append(member, address);
        }

        List<Long> unmatchedMemberList = matchService.autoMatch(null);

        System.out.println("=============== 매치 안된 멤버아이디 리스트 ==================");
        unmatchedMemberList.forEach(match -> System.out.println(match.toString()));
        System.out.println("===========================================");

        List<MemberMatchEntity> matchEntityList = matchRepository.findAll();
        System.out.println("=============== 매치 엔티티 리스트 ==================");
        matchEntityList.forEach(match ->
                System.out.println(match.getId().toString()
                        + "\n" + match.getManMemberId().toString()
                +"\n" + match.getWomanMemberId().toString()
                + "\n" + match.getMatchingStatus()
                        +"\n" + match.getStatus()
                + "\n" + match.getCreatedTime())
        );
        System.out.println("===========================================");


        List<MatchResultDto> matchList = matchService.getMatchResult(1, 10).getContent();
        System.out.println("=============== 매치리스트 ==================");
        matchList.forEach(match -> System.out.println(match.toString()));
        System.out.println("===========================================");
    }

    private Address getAddress() {
        return Address.of(
                "00000",
                "서울시",
                "강동구",
                "고덕동",
                new GeoPoint(100,100)
        );
    }

    private Member getMember(int i, Gender gender) {
        return Member.of(
                "kakaoId" + Integer.toString(i),
                "사람"+ Integer.toString(i),
                gender,
                LocalDateTime.now(),
                30,
                "01000000000",
                null,
                "회사",
                "직업",
                "대학교",
                Mbti.ISFJ,
                false,
                false,
                Religion.NON_RELIGIOUS,
                null,
                "",
                LocalDateTime.now(),
                5,
                3,
                ApprovalStatus.DEPOSIT_COMPLETED,
                ActiveStatus.CREATED
        );
    }

}
