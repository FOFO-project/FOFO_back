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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
                getFilteredMemberIdMap(matchPossibleMembers),
                getMatchingYnMap(matchPossibleMembers)
        );
    }

    public List<Long> findUnmatchedMemberIdList(List<Member> selectedMembers, List<Match> matchList) {
        return selectedMembers.stream()
                .filter(member -> matchList.stream()
                        .allMatch(match -> !Objects.equals(member.id(), match.man().id())
                                        && !Objects.equals(member.id(), match.woman().id())
                        )

                )
                .map(Member::id)
                .collect(Collectors.toList());
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
            Map<Long, Set<Long>> filterMap,
            Map<Long, Boolean> matchingYnMap)
    {
        List<Match> result = new ArrayList<>();
        for (Member member : selectedMembers){
            if(matchingYnMap.get(member.id())) continue;
            for (Member targetMember : matchPossibleMembers){
                if(matchingYnMap.get(targetMember.id())
                        || member.id().equals(targetMember.id())
                        || member.gender().equals(targetMember.gender())
                ) {
                    continue;
                }
                Pair<Member, Member> memberPair = identifyGender(member, targetMember);
                if (!filterMap.get(member.id()).contains(targetMember.id())
                        && !filterMap.get(targetMember.id()).contains(member.id())
                ){
                    result.add(Match.of(
                            memberPair.getLeft(),
                            memberPair.getRight(),
                            MatchAgreement.N,
                            MatchAgreement.N,
                            MatchingStatus.MATCHING_PENDING,
                            ActiveStatus.CREATED
                    ));
                    matchingYnMap.put(member.id(), true);
                    matchingYnMap.put(targetMember.id(), true);
                    break;
                }
            }
        }
        return result;
    }

    private List<Member> getShuffledList(List<Member> matchPossibleMembers) {
        List<Member> newMemberList = new ArrayList<>(matchPossibleMembers.stream().toList());
        Collections.shuffle(newMemberList);
        return newMemberList;
    }

    private Map<Long, Boolean> getMatchingYnMap(List<Member> matchPossibleMembers) {
        return matchPossibleMembers.stream()
                .collect(Collectors.toMap(
                        Member::id,
                        member -> false
                ));
    }

    private Map<Long, Set<Long>> getFilteredMemberIdMap(List<Member> matchPossibleMembers) {
        return matchPossibleMembers.stream()
                .collect(Collectors.toMap(
                        Member::id,
                        member -> matchPossibleMembers.stream()
                                .filter(targetMember -> !member.equals(targetMember)
                                        && (member.filteringSmoker().isCodeValue() && targetMember.smokingYn().isCodeValue())
                                        || (member.filteringReligion() != null && member.filteringReligion().equals(targetMember.religion()))
                                        || (member.filteringAgeRelation() != null && member.filteringAgeRelation().equals(getAgeRelation(member, targetMember))))
                                .map(Member::id)
                                .collect(Collectors.toSet())
                ));
    }

    private Pair<Member, Member> identifyGender(Member memberA, Member memberB) {
        if(memberA.gender().equals(Gender.MAN)){
            return Pair.of(memberA, memberB);
        }
        return Pair.of(memberB, memberA);
    }

    private AgeRelationType getAgeRelation(Member memberA, Member memberB) {
        if (memberA.age() > memberB.age()) {
            return AgeRelationType.OLDER;
        }
        if (memberA.age() == memberB.age()) {
            return AgeRelationType.SAME;
        }
        return AgeRelationType.YOUNGER;
    }

    public List<Member> getSelectedMembers(List<Long> memberIdList, List<Member> matchPossibleMembers) {
        if(memberIdList == null || memberIdList.isEmpty()){
            return matchPossibleMembers;
        }
        return matchPossibleMembers.stream()
                .filter(member -> memberIdList.stream().anyMatch(Predicate.isEqual(member.id())))
                .toList();
    }

}
