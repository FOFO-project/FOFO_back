package com.fofo.core.domain.member;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.image.Image;
import com.fofo.core.storage.AddressEntity;
import com.fofo.core.storage.ImageRepository;
import com.fofo.core.storage.MemberEntity;
import com.fofo.core.storage.MemberImageEntity;
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

import static com.fofo.core.support.error.CoreErrorType.MEMBER_NOT_FOUND_ERROR;

@Component
@RequiredArgsConstructor
public class FormFinder {

    private final MemberRepository memberRepository;
    private final ImageRepository imageRepository;

    public Form find(final long memberId) {
        Tuple findTuple = memberRepository.findMemberById(memberId);
        if (findTuple == null) {
            throw new CoreApiException(MEMBER_NOT_FOUND_ERROR);
        }

        MemberEntity memberEntity = findTuple.get(QMemberEntity.memberEntity);
        AddressEntity addressEntity = findTuple.get(QAddressEntity.addressEntity);

        MemberWithAddress memberWithAddress = MemberWithAddress.of(Member.from(memberEntity), Address.from(addressEntity));
        List<Image> images = imageRepository.findByMemberIdAndStatusNot(memberEntity.getId(), ActiveStatus.DELETED).stream()
                .map(Image::from)
                .toList();

        return Form.of(memberWithAddress, images);
    }

    public Page<Form> findAll(final FindMember findMember, final Pageable pageable) {
        List<Tuple> findTuples = memberRepository.findMembersWithCondition(findMember, pageable);
        List<MemberWithAddress> memberWithAddresses = findTuples.stream()
                .map(tuple -> {
                    MemberEntity memberEntity = tuple.get(QMemberEntity.memberEntity);
                    AddressEntity addressEntity = tuple.get(QAddressEntity.addressEntity);
                    return MemberWithAddress.of(Member.from(memberEntity), Address.from(addressEntity));
                })
                .toList();

        List<Long> memberIds = memberWithAddresses.stream()
                .map(v -> v.member().id())
                .toList();
        List<MemberImageEntity> imageEntities = imageRepository.findByMemberIdInAndStatusNot(memberIds, ActiveStatus.DELETED);

        List<Form> form = memberWithAddresses.stream()
                .map(memberWithAddress -> {
                    List<Image> images = imageEntities.stream()
                            .filter(image -> image.getMemberId() == memberWithAddress.member().id())
                            .map(Image::from)
                            .toList();

                    return Form.of(memberWithAddress, images);
                })
                .toList();

        return new PageImpl<>(form, pageable, memberWithAddresses.size());
    }
}
