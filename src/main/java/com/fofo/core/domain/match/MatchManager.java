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
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Component
@RequiredArgsConstructor
public class MatchManager {
    public List<Match> matchByFilteringCondition(final List<Member> matchPossibleMembers) {

        List<Match> result = new ArrayList<>();

        Random random = new Random();
        List<Integer> randomIdxList = random.ints(matchPossibleMembers.size())
                .boxed()
                .toList();

        for(int i = 0; i < matchPossibleMembers.size(); i++){
            Member member = matchPossibleMembers.get(i);
            if (member == null) continue;
            for(int j : randomIdxList){
                Member targetMember = matchPossibleMembers.get(j);
                if (i == j || targetMember == null){
                    continue;
                }

                if (member.gender().equals(targetMember.gender())){
                    continue;
                }

                if (member.filteringConditionSmokingYn() == targetMember.smokingYn()
                        || member.filteringConditionReligion().equals(targetMember.religion())
                        || member.filteringConditionAgeRelation().equals(getAgeRelation(member, targetMember))){
                    continue;
                }

                Pair<Member, Member> memberPair = identifyGender(member, targetMember);
                result.add(Match.of(
                        memberPair.getLeft(),
                        memberPair.getRight(),
                        MatchingStatus.MATCHING_PENDING,
                        ActiveStatus.CREATED
                ));
                matchPossibleMembers.set(i, null);
                matchPossibleMembers.set(j, null);
                break;
            }
        }

        return result;
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

    public MatchingStatus getNextMatchingStatus(final MatchingStatus matchingStatus) {
        return switch (matchingStatus){
            case MATCHING_PENDING -> MatchingStatus.MATCHING_PROGRESSING;
            case MATCHING_PROGRESSING -> MatchingStatus.MATCHING_COMPLETED;
            case MATCHING_COMPLETED -> throw new CoreApiException(CoreErrorType.MATCH_ALREADY_COMPLETED_ERROR);
        };
    }
}
