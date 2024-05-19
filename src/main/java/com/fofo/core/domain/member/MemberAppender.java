package com.fofo.core.domain.member;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.image.FileStore;
import com.fofo.core.domain.image.ImageType;
import com.fofo.core.domain.image.UploadFile;
import com.fofo.core.storage.AddressRepository;
import com.fofo.core.storage.ImageRepository;
import com.fofo.core.storage.MemberEntity;
import com.fofo.core.storage.MemberImageEntity;
import com.fofo.core.storage.MemberRepository;
import com.fofo.core.support.error.CoreApiException;
import com.fofo.core.support.error.CoreErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberAppender {

    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;
    private final ImageRepository imageRepository;
    private final FileStore fileStore;

    @Transactional
    public long append(final Member member, final Address address, final List<MultipartFile> multipartFile) throws IOException {
        MemberEntity memberEntity = member.toEntity();
        if (memberRepository.findByKakaoIdAndStatusNot(member.kakaoId(), ActiveStatus.DELETED).isPresent()) {
            throw new CoreApiException(CoreErrorType.DUPLICATE_MEMBER_ERROR);
        }

        memberEntity.setAddressId(addressRepository.save(address.toEntity()).getId());
        long memberId = memberRepository.save(memberEntity).getId();

        List<UploadFile> uploadFiles = fileStore.storeFiles(multipartFile, memberId);
        for (UploadFile uploadFile : uploadFiles) {
            MemberImageEntity imageEntity = MemberImageEntity.of(memberId, ImageType.USER_PROFILE, uploadFile.uploadFileName(), uploadFile.storeFileName(), ActiveStatus.CREATED);
            imageRepository.save(imageEntity);
        }

        return memberId;
    }

}
