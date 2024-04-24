package com.fofo.core.domain.match;

import com.fofo.core.controller.request.MatchRequestDto;
import com.fofo.core.domain.member.Member;
import com.fofo.core.support.error.CoreApiException;
import com.fofo.core.support.error.CoreErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchAppender matchAppender;
    private final MatchFinder matchFinder;
    private final MatchUpdater matchUpdater;
    private final MatchRemover matchRemover;
    private final MatchManager matchManager;

    // 매치 결과 조회
    public Page<MatchResult> getMatchResult(final int page, final int size, final MatchingStatus matchingStatus) {
        return matchFinder.findMatches(page, size, matchingStatus);
    }

    @Transactional
    public List<Long> autoMatch(final List<Long> memberIdList){
        // 매칭 가능한 멤버리스트 입금 순으로 찾기
        List<Member> matchPossibleMembers = matchFinder.findMatchPossibleMembers();
        // 매칭 선택된 멤버리스트 불러오기
        List<Member> selectedMembers = matchManager.getSelectedMembers(memberIdList, matchPossibleMembers);
        // 자동 매치
        List<Match> matchList = matchManager.autoMatchByFilteringCondition(selectedMembers, matchPossibleMembers);
        matchAppender.appendMatches(matchList);
        return matchManager.findUnmatchedMemberIdList(selectedMembers, matchList);
    }

    public void cancelMatch(final List<Long> matchIdList) {
        matchRemover.removeMatch(matchIdList);
    }

    public void manualMatch(final Long manMemberId, final Long womanMemberId) {
        matchAppender.appendMatch(manMemberId, womanMemberId);
    }

    @Transactional
    public void goNextMatchStep(final List<MatchRequestDto> matchRequestDtoList) {
        for(MatchRequestDto matchRequestDto : matchRequestDtoList){
            MatchingStatus nextMatchingStatus = matchManager.getNextMatchingStatus(matchRequestDto.matchingStatus());
            if(MatchingStatus.MATCHING_PROGRESSING.equals(nextMatchingStatus)){
                matchUpdater.updateMatchProgressing(
                        matchRequestDto.id(),
                        nextMatchingStatus
                );
            } else if(MatchingStatus.MATCHING_COMPLETED.equals(nextMatchingStatus)){
                // 남자, 여자 동의 상태를 보고 판단
                matchUpdater.updateMatchCompleted(
                        matchRequestDto.id(),
                        matchRequestDto.manId(),
                        matchRequestDto.manAgreement(),
                        matchRequestDto.womanId(),
                        matchRequestDto.womanAgreement(),
                        nextMatchingStatus
                );
            } else{
                throw new CoreApiException(CoreErrorType.ENUM_MAPPING_ERROR);
            }
        }
    }

}
