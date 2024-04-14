package com.fofo.core.domain.member;

import com.fofo.core.storage.AddressEntity;
import com.fofo.core.storage.MemberEntity;
import com.fofo.core.storage.MemberRepository;
import com.fofo.core.storage.QAddressEntity;
import com.fofo.core.storage.QMemberEntity;
import com.fofo.core.support.error.CoreApiException;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.fofo.core.support.error.CoreErrorType.ADDRESS_NOT_FOUND_ERROR;
import static com.fofo.core.support.error.CoreErrorType.MEMBER_NOT_FOUND_ERROR;

@Component
@RequiredArgsConstructor
public class MemberFinder {

    private final MemberRepository memberRepository;

    public MemberWithAddress find(final long memberId) {
        Tuple findTuple = memberRepository.findMemberById(memberId);
        MemberEntity memberEntity = Optional.ofNullable(findTuple.get(QMemberEntity.memberEntity))
                .orElseThrow(() -> new CoreApiException(MEMBER_NOT_FOUND_ERROR));
        AddressEntity addressEntity = Optional.ofNullable(findTuple.get(QAddressEntity.addressEntity))
                .orElseThrow(() -> new CoreApiException(ADDRESS_NOT_FOUND_ERROR));

        return MemberWithAddress.of(Member.from(memberEntity), Address.from(addressEntity));
    }

    public Page<MemberWithAddress> findAll(final FindMember findMember, final Pageable pageable) {
        List<Tuple> findTuples = memberRepository.findMembersWithCondition(findMember, pageable);
        List<MemberWithAddress> memberWithAddresses = findTuples.stream()
                .map(tuple -> {
                    MemberEntity memberEntity = Objects.requireNonNull(tuple.get(QMemberEntity.memberEntity));
                    AddressEntity addressEntity = Objects.requireNonNull(tuple.get(QAddressEntity.addressEntity));
                    return MemberWithAddress.of(Member.from(memberEntity), Address.from(addressEntity));
                })
                .toList();

        return new PageImpl<>(memberWithAddresses, pageable, memberWithAddresses.size());
    }
}
