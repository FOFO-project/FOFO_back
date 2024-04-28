package com.fofo.core.domain.member;

import com.fofo.core.domain.image.ImageType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberAppender memberAppender;
    private final MemberFinder memberFinder;
    private final MemberUpdater memberUpdater;

    public long append(final Member member, final Address address) {
        return memberAppender.append(member, address);
    }

    public MemberWithAddress find(final long memberId) {
        return memberFinder.find(memberId);
    }

    public Page<MemberWithAddress> findAll(final FindMember findMember, final int pageNumber, final int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return memberFinder.findAll(findMember, pageRequest);
    }

    public long update(final long memberId, final UpdateMember updateMember, final UpdateAddress updateAddress) {
        return memberUpdater.update(memberId, updateMember, updateAddress);
    }

    public List<Long> remove(final List<Long> memberIds) {
        return memberUpdater.remove(memberIds);
    }

    public List<Long> confirmDeposit(final List<Long> memberIds) {
        return memberUpdater.confirmDeposit(memberIds);
    }

    public long approve(final long memberId, String note, ImageType imageType, final MultipartFile imageFile) throws IOException {
        return memberUpdater.approve(memberId, note, imageType, imageFile);
    }
}
