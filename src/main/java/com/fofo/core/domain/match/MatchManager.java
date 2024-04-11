package com.fofo.core.domain.match;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.member.AgeRelationType;
import com.fofo.core.domain.member.Gender;
import com.fofo.core.domain.member.Member;
import com.fofo.core.support.error.CoreApiException;
import com.fofo.core.support.error.CoreErrorType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MatchManager {

    public List<Match> autoMatchByFilteringCondition(final List<Member> matchPossibleMembers) {
        List<Match> result = new ArrayList<>();
        Map<Member, Set<Member>> filterMap = getFilteredMemberMap(matchPossibleMembers);
        Map<Member, Boolean> matchingYnMap = getMatchingYnMap(matchPossibleMembers);
        List<Member> randomMemberlist = getRandomList(matchPossibleMembers);

        for (Member member : matchPossibleMembers){
            if(matchingYnMap.get(member)) continue;
            for (Member targetMember : randomMemberlist){
                if(matchingYnMap.get(targetMember)) continue;
                Pair<Member, Member> memberPair = identifyGender(member, targetMember);
                if (!filterMap.get(member).contains(targetMember) && !filterMap.get(targetMember).contains(member)){
                    result.add(Match.of(
                            memberPair.getLeft(),
                            memberPair.getRight(),
                            MatchingStatus.MATCHING_PENDING,
                            ActiveStatus.CREATED
                    ));
                    matchingYnMap.put(member, true);
                    matchingYnMap.put(targetMember, true);
                    break;
                }
            }
        }
        return result;
    }

    public MatchingStatus getNextMatchingStatus(final MatchingStatus matchingStatus) {
        return switch (matchingStatus){
            case MATCHING_PENDING -> MatchingStatus.MATCHING_PROGRESSING;
            case MATCHING_PROGRESSING -> MatchingStatus.MATCHING_COMPLETED;
            case MATCHING_COMPLETED -> throw new CoreApiException(CoreErrorType.MATCH_ALREADY_COMPLETED_ERROR);
        };
    }

    private List<Member> getRandomList(List<Member> matchPossibleMembers) {
        Random random = new Random();
        List<Integer> randomIdxList = random.ints(matchPossibleMembers.size())
                .boxed()
                .toList();
        return randomIdxList.stream()
                .map(matchPossibleMembers::get)
                .toList();
    }

    private Map<Member, Boolean> getMatchingYnMap(List<Member> matchPossibleMembers) {
        return matchPossibleMembers.stream()
                .collect(Collectors.toMap(
                        member -> member,
                        member -> false
                ));
    }

    private Map<Member, Set<Member>> getFilteredMemberMap(List<Member> matchPossibleMembers) {
        return matchPossibleMembers.stream()
                .collect(Collectors.toMap(
                        member -> member,
                        member -> matchPossibleMembers.stream()
                                .filter(targetMember -> member.equals(targetMember)
                                        || member.filteringConditionSmokingYn() == targetMember.smokingYn()
                                        || member.filteringConditionReligion().equals(targetMember.religion())
                                        || member.filteringConditionAgeRelation().equals(getAgeRelation(member, targetMember)))
                                .collect(Collectors.toSet())
                ));
    }

    private Pair<Member, Member> identifyGender(Member memberA, Member memberB) {
        if(memberA.gender().equals(Gender.MAN)){
            return Pair.of(memberA, memberB);
        } else {
            return Pair.of(memberB, memberA);
        }
    }

    private AgeRelationType getAgeRelation(Member memberA, Member memberB) {
        if (memberA.age() > memberB.age()) {
            return AgeRelationType.ORDER;
        } else if (memberA.age() == memberB.age()) {
            return AgeRelationType.SAME;
        } else {
            return AgeRelationType.YOUNGER;
        }
    }

    public List<Member> getSelectedMebers(List<Long> memberIdList, List<Member> matchPossibleMembers) {
        return null;
//        return matchPossibleMembers.stream()
//                .filter(member -> memberIdList.stream().findFirst())
//                .toList();
    }

    public List<Match> selectedAutoMatchByFilteringCondition(List<Member> selectedMembers, List<Member> matchPossibleMembers) {
    }
}
