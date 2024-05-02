package com.fofo.core.domain.member;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.image.FileStore;
import com.fofo.core.domain.image.ImageType;
import com.fofo.core.domain.image.UploadFile;
import com.fofo.core.storage.AddressEntity;
import com.fofo.core.storage.AddressRepository;
import com.fofo.core.storage.ImageRepository;
import com.fofo.core.storage.MemberEntity;
import com.fofo.core.storage.MemberImageEntity;
import com.fofo.core.storage.MemberRepository;
import com.fofo.core.support.error.CoreApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.fofo.core.support.error.CoreErrorType.ADDRESS_NOT_FOUND_ERROR;
import static com.fofo.core.support.error.CoreErrorType.DEPOSIT_DATE_EXISTS_ERROR;
import static com.fofo.core.support.error.CoreErrorType.MEMBER_CANNOT_MATCHABLE_ERROR;
import static com.fofo.core.support.error.CoreErrorType.MEMBER_NOT_FOUND_ERROR;
import static com.fofo.core.support.error.CoreErrorType.NOT_PENDING_FOR_DEPOSIT_ERROR;
import static com.fofo.core.support.error.CoreErrorType.NOT_WAITING_FOR_APPROVE_ERROR;

@Component
@RequiredArgsConstructor
public class MemberUpdater {

    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;
    private final ImageRepository imageRepository;
    private final FileStore fileStore;

    @Transactional
    public List<Long> remove(final List<Long> memberIds) {
        return memberIds.stream()
                .filter(memberId -> {
                    try {
                        remove(memberId);
                        return false; // 성공적으로 제거된 경우, 실패 목록에 포함시키지 않음
                    } catch (CoreApiException e) {
                        return true; // 제거에 실패한 경우, 실패 목록에 포함
                    }
                })
                .toList(); // 실패한 멤버 ID 들을 리스트로 수집
    }

    private void remove(final long memberId) {
        MemberEntity findMember = memberRepository.findByIdAndStatusNot(memberId, ActiveStatus.DELETED)
                .orElseThrow(() -> new CoreApiException(MEMBER_NOT_FOUND_ERROR));
        findMember.setStatus(ActiveStatus.DELETED);

        AddressEntity findAddress = addressRepository.findByIdAndStatusNot(findMember.getAddressId(), ActiveStatus.DELETED)
                .orElseThrow(() -> new CoreApiException(ADDRESS_NOT_FOUND_ERROR));
        findAddress.setStatus(ActiveStatus.DELETED);
    }

    @Transactional
    public List<Long> confirmDeposit(final List<Long> memberIds) {
        return memberIds.stream()
                .filter(memberId -> {
                    try {
                        confirmDeposit(memberId, LocalDateTime.now());
                        return false; // 입금확인이 승인된 경우, 실패 목록에 포함시키지 않음
                    } catch (CoreApiException e) {
                        return true; // 입금확인에 실패한 경우, 실패 목록에 포함
                    }
                })
                .toList(); // 실패한 멤버 ID 들을 리스트로 수집
    }

    private void confirmDeposit(final long memberId, final LocalDateTime depositDate) {
        MemberEntity findMember = memberRepository.findByIdAndStatusNot(memberId, ActiveStatus.DELETED)
                .orElseThrow(() -> new CoreApiException(MEMBER_NOT_FOUND_ERROR));
        if (findMember.getDepositDate() != null) {
            throw new CoreApiException(DEPOSIT_DATE_EXISTS_ERROR);
        }
        if (findMember.getApprovalStatus() != ApprovalStatus.DEPOSIT_PENDING) {
            throw new CoreApiException(NOT_PENDING_FOR_DEPOSIT_ERROR);
        }

        findMember.setDepositDate(depositDate);
        findMember.setApprovalStatus(ApprovalStatus.DEPOSIT_COMPLETED);
        findMember.setStatus(ActiveStatus.UPDATED);
    }

    @Transactional
    public List<Long> approve(final List<Long> memberIds) {
        return memberIds.stream()
                .filter(memberId -> {
                    try {
                        approve(memberId);
                        return false; // 성공적으로 승인된 경우, 실패 목록에 포함시키지 않음
                    } catch (CoreApiException e) {
                        return true; // 제거에 실패한 경우, 실패 목록에 포함
                    }
                })
                .toList(); // 실패한 멤버 ID 들을 리스트로 수집
    }

    private void approve(final long memberId) {
        MemberEntity findMember = memberRepository.findByIdAndStatusNot(memberId, ActiveStatus.DELETED)
                .orElseThrow(() -> new CoreApiException(MEMBER_NOT_FOUND_ERROR));
        if (findMember.getApprovalStatus() != ApprovalStatus.DEPOSIT_COMPLETED || findMember.getDepositDate() == null) {
            throw new CoreApiException(NOT_WAITING_FOR_APPROVE_ERROR);
        }

        findMember.setApprovalStatus(ApprovalStatus.APPROVED);
        findMember.setMatchableYn(MatchableYn.Y);
        findMember.setStatus(ActiveStatus.UPDATED);
    }

    @Transactional
    public long update(final long memberId, final UpdateMember updateMember, final UpdateAddress updateAddress, final MultipartFile cardImage) throws IOException {
        MemberEntity findMember = memberRepository.findByIdAndStatusNot(memberId, ActiveStatus.DELETED)
                .orElseThrow(() -> new CoreApiException(MEMBER_NOT_FOUND_ERROR));
        updateMemberInfo(findMember, updateMember);

        if (updateAddress != null) {
            AddressEntity findAddress = addressRepository.findByIdAndStatusNot(findMember.getAddressId(), ActiveStatus.DELETED)
                    .orElseThrow(() -> new CoreApiException(ADDRESS_NOT_FOUND_ERROR));
            updateAddressInfo(findAddress, updateAddress);
        }

        UploadFile uploadFile = fileStore.storeFile(cardImage, memberId);
        MemberImageEntity imageEntity = MemberImageEntity.of(memberId, ImageType.PROFILE_CARD, uploadFile.uploadFileName(), uploadFile.storeFileName(), ActiveStatus.CREATED);
        imageRepository.save(imageEntity);

        return memberId;
    }

    private void updateMemberInfo(final MemberEntity findMember, final UpdateMember updateMember) {
        Optional.ofNullable(updateMember.name()).ifPresent(findMember::setName);
        Optional.ofNullable(updateMember.gender()).ifPresent(findMember::setGender);
        Optional.ofNullable(updateMember.birthday()).ifPresent(findMember::setBirthday);
        Optional.ofNullable(updateMember.age()).ifPresent(findMember::setAge);
        Optional.ofNullable(updateMember.height()).ifPresent(findMember::setHeight);
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
        Optional.ofNullable(updateAddress.sido()).ifPresent(findAddress::setSido);
        Optional.ofNullable(updateAddress.sigungu()).ifPresent(findAddress::setSigungu);
        Optional.ofNullable(updateAddress.eupmyundong()).ifPresent(findAddress::setEupmyundong);
        findAddress.setStatus(updateAddress.status());
    }

    @Transactional
    public List<Long> updateMatchable(final List<Long> memberIds) {
        return memberIds.stream()
                .filter(memberId -> {
                    try {
                        updateMatchable(memberId);
                        return false;
                    } catch (CoreApiException e) {
                        return true;
                    }
                })
                .toList();
    }

    private void updateMatchable(Long memberId) {
        MemberEntity findMember = memberRepository.findByIdAndStatusNot(memberId, ActiveStatus.DELETED)
                .orElseThrow(() -> new CoreApiException(MEMBER_NOT_FOUND_ERROR));
        if(findMember.getMatchableYn() == MatchableYn.N
                || findMember.getChance() < 1
                || findMember.getApprovalStatus() != ApprovalStatus.APPROVED){
            throw new CoreApiException(MEMBER_CANNOT_MATCHABLE_ERROR);
        }

        findMember.setMatchableYn(MatchableYn.Y);
        findMember.setStatus(ActiveStatus.UPDATED);
    }
}
