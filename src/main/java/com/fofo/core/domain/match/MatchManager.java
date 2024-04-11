package com.fofo.core.domain.match;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.member.AgeRelationType;
import com.fofo.core.domain.member.Gender;
import com.fofo.core.domain.member.Member;
import com.fofo.core.support.error.CoreApiException;
import com.fofo.core.support.error.CoreErrorType;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class MatchManager {

    public List<Match> autoMatchByFilteringCondition(
            final List<Member> selectedMembers,
            final List<Member> matchPossibleMembers
    ) {
        return getMatchList(
                selectedMembers,
                getShuffledList(matchPossibleMembers),
                getFilteredMemberMap(matchPossibleMembers),
                getMatchingYnMap(matchPossibleMembers)
        );
    }

    public MatchingStatus getNextMatchingStatus(final MatchingStatus matchingStatus) {
        return switch (matchingStatus){
            case MATCHING_PENDING -> MatchingStatus.MATCHING_PROGRESSING;
            case MATCHING_PROGRESSING -> MatchingStatus.MATCHING_COMPLETED;
            case MATCHING_COMPLETED -> throw new CoreApiException(CoreErrorType.MATCH_ALREADY_COMPLETED_ERROR);
        };
    }

    private List<Match> getMatchList(
            List<Member> selectedMembers,
            List<Member> matchPossibleMembers,
            Map<Member, Set<Member>> filterMap,
            Map<Member, Boolean> matchingYnMap)
    {
        List<Match> result = new ArrayList<>();
        for (Member member : selectedMembers){
            if(matchingYnMap.get(member)) continue;
            for (Member targetMember : matchPossibleMembers){
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

    private List<Member> getShuffledList(List<Member> matchPossibleMembers) {
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

    public List<Member> getSelectedMembers(List<Long> memberIdList, List<Member> matchPossibleMembers) {
        return matchPossibleMembers.stream()
                .filter(member -> memberIdList.stream().anyMatch(Predicate.isEqual(member.id())))
                .toList();
    }

}
