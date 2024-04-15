package com.fofo.core.domain.member;

import com.fofo.core.storage.AddressEntity;
import com.fofo.core.storage.AddressRepository;
import com.fofo.core.storage.MemberEntity;
import com.fofo.core.storage.MemberRepository;
import com.fofo.core.support.error.CoreApiException;
import com.fofo.core.support.error.CoreErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberUpdater {

    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public long update(final long memberId, final UpdateMember updateMember, final UpdateAddress updateAddress) {
        MemberEntity findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new CoreApiException(CoreErrorType.MEMBER_NOT_FOUND_ERROR));
        updateMemberInfo(findMember, updateMember);

        if (updateAddress != null) {
            AddressEntity findAddress = addressRepository.findById(findMember.getAddressId())
                    .orElseThrow(() -> new CoreApiException(CoreErrorType.ADDRESS_NOT_FOUND_ERROR));

            updateAddressInfo(findAddress, updateAddress);
        }

        return memberId;
    }

    private void updateMemberInfo(final MemberEntity findMember, final UpdateMember updateMember) {
        Optional.ofNullable(updateMember.name()).ifPresent(findMember::setName);
        Optional.ofNullable(updateMember.gender()).ifPresent(findMember::setGender);
        Optional.ofNullable(updateMember.birthday()).ifPresent(findMember::setBirthday);
        Optional.ofNullable(updateMember.age()).ifPresent(findMember::setAge);
        Optional.ofNullable(updateMember.phoneNumber()).ifPresent(findMember::setPhoneNumber);
        Optional.ofNullable(updateMember.filteringAgeRelation()).ifPresent(findMember::setFilteringAgeRelation);
        Optional.ofNullable(updateMember.company()).ifPresent(findMember::setCompany);
        Optional.ofNullable(updateMember.job()).ifPresent(findMember::setJob);
        Optional.ofNullable(updateMember.university()).ifPresent(findMember::setUniversity);
        Optional.ofNullable(updateMember.mbti()).ifPresent(findMember::setMbti);
        Optional.ofNullable(updateMember.smokingYn()).ifPresent(findMember::setSmokingYn);
        Optional.ofNullable(updateMember.filteringSmoker()).ifPresent(findMember::setFilteringSmoker);
        Optional.ofNullable(updateMember.religion()).ifPresent(findMember::setReligion);
        Optional.ofNullable(updateMember.filteringReligion()).ifPresent(findMember::setFilteringReligion);
        Optional.ofNullable(updateMember.charmingPoint()).ifPresent(findMember::setCharmingPoint);
        Optional.ofNullable(updateMember.note()).ifPresent(findMember::setNote);
        findMember.setStatus(updateMember.status());
    }

    private void updateAddressInfo(final AddressEntity findAddress, final UpdateAddress updateAddress) {
        Optional.ofNullable(updateAddress.zipcode()).ifPresent(findAddress::setZipCode);
        Optional.ofNullable(updateAddress.sido()).ifPresent(findAddress::setSido);
        Optional.ofNullable(updateAddress.sigungu()).ifPresent(findAddress::setSigungu);
        Optional.ofNullable(updateAddress.eupmyundong()).ifPresent(findAddress::setEupmyundong);
        Optional.ofNullable(updateAddress.location()).ifPresent(location -> findAddress.setLocation(new Point(location)));
        findAddress.setStatus(updateAddress.status());
    }
}
