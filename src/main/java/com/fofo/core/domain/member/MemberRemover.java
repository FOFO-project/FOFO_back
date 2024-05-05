package com.fofo.core.domain.member;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.image.FileStore;
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

import java.util.List;

import static com.fofo.core.support.error.CoreErrorType.ADDRESS_NOT_FOUND_ERROR;
import static com.fofo.core.support.error.CoreErrorType.MEMBER_NOT_FOUND_ERROR;

@Component
@RequiredArgsConstructor
public class MemberRemover {

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

        List<MemberImageEntity> findImages = imageRepository.findByMemberIdAndStatusNot(memberId, ActiveStatus.DELETED);
        for (MemberImageEntity findImage : findImages) {
            findImage.setStatus(ActiveStatus.DELETED);
            fileStore.deleteFile(findImage.getStoreFileName());
        }
    }

}
