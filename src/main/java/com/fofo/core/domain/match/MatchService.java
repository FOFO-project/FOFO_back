package com.fofo.core.domain.match;

import com.fofo.core.controller.request.MatchRequestDto.ManualMatchRequestDto;
import com.fofo.core.controller.response.MatchResponseDto.MatchResponseDto;
import com.fofo.core.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    // 모든 매치 결과 조회
    public Page<Match> getMatchResult(int page, int size) {
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

    public void cancelMatch(List<Long> matchIdList) {
        // 매칭 전 BEFORE(테이블엔 없음), 매칭 후 대기 HOLD, 프로필 발송 후 대기 PROCEED, 매칭 확정 COMPLETE
        // HOLD 상태에서 매치를 삭제한다.
        matchRemover.removeMatch(matchIdList, "HOLD");
    }

    public void sendProfile(List<Long> matchIdList) {
        // HOLD 상태에서 PROCEED

    }

    public void confirmMatch(List<Long> matchIdList) {
        matchUpdater.updateMatchComplete(matchIdList);
    }

    public void manualMatch(ManualMatchRequestDto manualMatchRequestDto) {
        matchAppender.appendMatch(manualMatchRequestDto.toEntity());
    }
}
