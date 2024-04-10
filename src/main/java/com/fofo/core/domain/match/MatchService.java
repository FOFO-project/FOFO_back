package com.fofo.core.domain.match;

import com.fofo.core.controller.request.MatchRequestDto.ManualMatchRequestDto;
import com.fofo.core.controller.response.MatchResponseDto.MatchResponseDto;
import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.member.Member;
import com.fofo.core.domain.member.MemberFinder;
import com.fofo.core.storage.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchAppender matchAppender;
    private final MatchFinder matchFinder;
    private final MemberFinder memberFinder;
    private final MatchUpdater matchUpdater;
    private final MatchRemover matchRemover;
    private final MatchManager matchManager;

    // 모든 매치 결과 조회
    public Page<Match> getMatchResult(final int page, final int size) {
        return matchFinder.findMatches(page, size);
    }

    // 자동 매치
    public void autoMatch(){
        // 매칭 가능한 멤버리스트 입금 순으로 찾기
        List<Member> matchPossibleMembers = matchFinder.findMatchPossibleMembers();
        // 매치 매니저 - 싫어하는 조건 제외 사람 중 랜덤하게 매칭
        List<Match> matchList = matchManager.matchByFilteringCondition(matchPossibleMembers);
        // 매치 어펜더 - 매치 추가
        matchAppender.appendMatches(matchList);
    }

    public void cancelMatch(final List<Long> matchIdList) {
        matchRemover.removeMatch(matchIdList);
    }

    public void manualMatch(final Long manMemberId, final Long womanMemberId) {

        MemberEntity manMemberEntity = memberFinder.findMember(manMemberId);
        MemberEntity womanMemberEntity = memberFinder.findMember(womanMemberId);
//        Match match = Match.of(
//                Member.from(manMemberEntity),
//                Member.from(womanMemberEntity),
//                MatchingStatus.MATCHING_PENDING,
//                ActiveStatus.CREATED
//        );
//        matchAppender.appendMatch(match.toEntity());
    }

    public void goNextMatchStep(final List<Long> matchIdList, final MatchingStatus matchingStatus) {
        MatchingStatus nextMatchingStatus = matchManager.getNextMatchingStatus(matchingStatus);
        matchUpdater.updateMatchStatus(matchIdList, nextMatchingStatus);
    }
}
