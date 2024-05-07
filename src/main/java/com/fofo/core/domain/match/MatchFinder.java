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

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MatchFinder {

    private final MatchRepository matchRepository;
    private final ImageRepository imageRepository;

    public Page<MatchResult> findMatches(final int page, final int size, final MatchingStatus matchingStatus) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Pair<List<Tuple>, Long> pair = matchRepository.findMatchResultList(pageRequest, matchingStatus);
        List<MatchResult> matchResultList = pair.getLeft().stream()
                .map(tuple -> {
                    MemberMatchEntity matchEntity = Objects.requireNonNull(tuple.get(0, MemberMatchEntity.class));
                    MemberEntity manEntity = Objects.requireNonNull(tuple.get(1, MemberEntity.class));
                    MemberEntity womanEntity = Objects.requireNonNull(tuple.get(2, MemberEntity.class));
                    AddressEntity manAddressEntity = Objects.requireNonNull(tuple.get(3, AddressEntity.class));
                    AddressEntity womanAddressEntity = Objects.requireNonNull(tuple.get(4, AddressEntity.class));

                    List<MemberImageEntity> manImageEntities = imageRepository.findByMemberIdAndStatusNot(manEntity.getId(), ActiveStatus.DELETED);
                    List<MemberImageEntity> womanImageEntities = imageRepository.findByMemberIdAndStatusNot(womanEntity.getId(), ActiveStatus.DELETED);

                    return MatchResult.of(
                            matchEntity.getId(),
                            Form.of(
                                    MemberWithAddress.of(Member.from(manEntity), Address.from(manAddressEntity)),
                                    manImageEntities.stream().map(Image::from).toList()
                            ),
                            Form.of(
                                    MemberWithAddress.of(Member.from(womanEntity), Address.from(womanAddressEntity)),
                                    womanImageEntities.stream().map(Image::from).toList()
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

}
