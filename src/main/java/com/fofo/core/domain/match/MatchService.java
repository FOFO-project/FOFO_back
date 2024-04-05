package com.fofo.core.domain.match;

import com.fofo.core.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchAppender matchAppender;
    private final MatchFinder matchFinder;
    private final MatchUpdater matchUpdater;
    private final MatchRemover matchRemover;
    private final MatchManager matchManager;

    // 자동 매치
    public void autoMatch(){
        // 매칭 가능한 멤버리스트 입금 순으로 찾기
        List<Member> matchPossibleMembers = matchFinder.findMatchPossibleMembers();
        // 매치 매니저 - 싫어하는 조건 제외 사람 중 랜덤하게 매칭
        List<Match> matchList = matchManager.matchByFilteringCondition(matchPossibleMembers);
        // 매치 어펜더 - 매치 추가
        matchAppender.appendMatch(matchList);
    }

    public void cancelMatch(List<Long> matchIdList) {
        // 매칭 전 BEFORE, 매칭 대기 HOLD, 매칭 진행 중(참여 의사 확인) PROCEED, 매칭 성사 COMPLETE
        // HOLD 상태에서 매치를 삭제한다.
        matchRemover.removeMatch(matchIdList, "HOLD");
    }

    public void confirmMatch(List<Long> matchIdList) {
        matchUpdater.updateMatchComplete(matchIdList);
    }
}
