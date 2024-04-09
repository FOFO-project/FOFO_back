package com.fofo.core.domain.member;

import com.fofo.core.storage.AddressRepository;
import com.fofo.core.storage.MemberEntity;
import com.fofo.core.storage.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class MemberAppender {

    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public Long append(final Member member, final Address address) {
        MemberEntity memberEntity = member.toEntity();
        memberEntity.setAddressId(addressRepository.save(address.toEntity()).id());

        return memberRepository.save(memberEntity).id();
    }

}
