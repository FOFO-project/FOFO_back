package com.fofo.core.domain.match;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.image.Image;
import com.fofo.core.domain.member.Address;
import com.fofo.core.domain.member.Form;
import com.fofo.core.domain.member.Member;
import com.fofo.core.domain.member.MemberWithAddress;
import com.fofo.core.storage.AddressEntity;
import com.fofo.core.storage.ImageRepository;
import com.fofo.core.storage.MatchRepository;
import com.fofo.core.storage.MemberEntity;
import com.fofo.core.storage.MemberImageEntity;
import com.fofo.core.storage.MemberMatchEntity;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MatchFinder {

    private final MatchRepository matchRepository;
    private final ImageRepository imageRepository;

    public Page<MatchResult> findMatches(final int page, final int size, final MatchingStatus matchingStatus) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Pair<List<Tuple>, Long> pair = matchRepository.findMatchResultList(pageRequest, matchingStatus);

        Set<Long> memberIdSet = new HashSet<>();
        for (Tuple tuple : pair.getLeft()){
            MemberMatchEntity matchEntity = Objects.requireNonNull(tuple.get(0, MemberMatchEntity.class));
            memberIdSet.add(matchEntity.getManMemberId());
            memberIdSet.add(matchEntity.getWomanMemberId());
        }
        List<Long> memberIds = memberIdSet.stream().toList();
        List<MemberImageEntity> imageEntities = imageRepository.findByMemberIdInAndStatusNot(memberIds, ActiveStatus.DELETED);
        Map<Long, List<MemberImageEntity>> memberImagesMap = imageEntities.stream()
                .collect(Collectors.groupingBy(MemberImageEntity::getMemberId));

        List<MatchResult> matchResultList = pair.getLeft().stream()
                .map(tuple -> {
                    MemberMatchEntity matchEntity = Objects.requireNonNull(tuple.get(0, MemberMatchEntity.class));
                    MemberEntity manEntity = Objects.requireNonNull(tuple.get(1, MemberEntity.class));
                    MemberEntity womanEntity = Objects.requireNonNull(tuple.get(2, MemberEntity.class));
                    AddressEntity manAddressEntity = Objects.requireNonNull(tuple.get(3, AddressEntity.class));
                    AddressEntity womanAddressEntity = Objects.requireNonNull(tuple.get(4, AddressEntity.class));

                    return MatchResult.of(
                            matchEntity.getId(),
                            Form.of(
                                    MemberWithAddress.of(Member.from(manEntity), Address.from(manAddressEntity)),
                                    memberImagesMap.get(manEntity.getId()).stream()
                                            .map(Image::from)
                                            .toList()
                            ),
                            Form.of(
                                    MemberWithAddress.of(Member.from(womanEntity), Address.from(womanAddressEntity)),
                                    memberImagesMap.get(womanEntity.getId()).stream()
                                            .map(Image::from)
                                            .toList()
                            ),
                            matchEntity.getManAgreement(),
                            matchEntity.getWomanAgreement(),
                            matchEntity.getMatchingStatus(),
                            matchEntity.getCreatedTime(),
                            matchEntity.getUpdatedTime()
                    );
                })
                .toList();
        Long count = pair.getRight();
        return new PageImpl<>(matchResultList, pageRequest, count == null? 0 : count);
    }

    public List<Match> getCompletedOrCanceledMatches() {
        return matchRepository.findCompletedOrCanceledMatchList().stream()
                .map(Match::from)
                .toList();
    }

    public List<Pair<MemberMatchEntity, Pair<MemberEntity, MemberEntity>>> findFailedMembersIn(List<Long> matchIds) {
        return matchRepository.findFailedMembersIn(matchIds).stream()
                .map(tuple ->Pair.of(
                        tuple.get(0, MemberMatchEntity.class),
                        Pair.of(tuple.get(1, MemberEntity.class), tuple.get(2, MemberEntity.class)))
                )
                .toList();
    }
}
