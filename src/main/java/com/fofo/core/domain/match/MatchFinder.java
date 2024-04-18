package com.fofo.core.domain.match;

import com.fofo.core.domain.member.Address;
import com.fofo.core.domain.member.Member;
import com.fofo.core.domain.member.MemberWithAddress;
import com.fofo.core.storage.AddressEntity;
import com.fofo.core.storage.MatchRepository;
import com.fofo.core.storage.MemberEntity;
import com.fofo.core.storage.MemberMatchEntity;
import com.fofo.core.support.error.CoreApiException;
import com.fofo.core.support.error.CoreErrorType;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MatchFinder {

    private final MatchRepository matchRepository;

    public List<Member> findMatchPossibleMembers() {
        List<MemberEntity> memberEntityList = matchRepository.findMatchPossibleMembers();
        if (memberEntityList.isEmpty()){
            throw new CoreApiException(CoreErrorType.MATCHABLE_MEMBER_NOT_FOUND);
        }
        return memberEntityList.stream()
                .map(Member::from)
                .toList();
    }

    public Page<MatchResult> findMatches(final int page, final int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Pair<List<Tuple>, Long> pair = matchRepository.findMatchResultList(pageRequest);
        List<MatchResult> matchResultList = pair.getLeft().stream()
                .map(tuple -> {
                    MemberMatchEntity matchEntity = Objects.requireNonNull(tuple.get(0, MemberMatchEntity.class));
                    MemberEntity manEntity = Objects.requireNonNull(tuple.get(1, MemberEntity.class));
                    MemberEntity womanEntity = Objects.requireNonNull(tuple.get(2, MemberEntity.class));
                    AddressEntity manAddressEntity = Objects.requireNonNull(tuple.get(3, AddressEntity.class));
                    AddressEntity womanAddressEntity = Objects.requireNonNull(tuple.get(4, AddressEntity.class));
                    return MatchResult.of(
                            matchEntity.getId(),
                            MemberWithAddress.of(Member.from(manEntity), Address.from(manAddressEntity)),
                            MemberWithAddress.of(Member.from(womanEntity), Address.from(womanAddressEntity)),
                            matchEntity.getMatchingStatus(),
                            matchEntity.getCreatedTime(),
                            matchEntity.getUpdatedTime()
                    );
                })
                .toList();
        Long count = pair.getRight();
        return new PageImpl<>(matchResultList, pageRequest, count == null? 0 : count);
    }

}
