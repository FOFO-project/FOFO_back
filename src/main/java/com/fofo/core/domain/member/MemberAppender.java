package com.fofo.core.domain.member;

import com.fofo.core.storage.AddressRepository;
import com.fofo.core.storage.MemberEntity;
import com.fofo.core.storage.MemberRepository;
import com.fofo.core.support.error.CoreApiException;
import com.fofo.core.support.error.CoreErrorType;
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
        if (memberRepository.findByKakaoId(member.kakaoId()).isPresent()) {
            throw new CoreApiException(CoreErrorType.DUPLICATE_MEMBER_ERROR);
        }

        memberEntity.setAddressId(addressRepository.save(address.toEntity()).id());
        return memberRepository.save(memberEntity).id();
    }

}
