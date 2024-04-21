package com.fofo.core.domain.member;

import com.fofo.core.support.error.CoreApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        return memberIds.stream()
                .filter(memberId -> {
                    try {
                        memberUpdater.remove(memberId);
                        return false; // 성공적으로 제거된 경우, 실패 목록에 포함시키지 않음
                    } catch (CoreApiException e) {
                        return true; // 제거에 실패한 경우, 실패 목록에 포함
                    }
                })
                .toList(); // 실패한 멤버 ID 들을 리스트로 수집
    }

    public long confirmDeposit(final long memberId, final LocalDateTime depositDate) {
        return memberUpdater.confirmDeposit(memberId, depositDate);
    }

    public List<Long> approve(final List<Long> memberIds) {
        return memberIds.stream()
                .filter(memberId -> {
                    try {
                        memberUpdater.approve(memberId);
                        return false; // 성공적으로 승인된 경우, 실패 목록에 포함시키지 않음
                    } catch (CoreApiException e) {
                        return true; // 제거에 실패한 경우, 실패 목록에 포함
                    }
                })
                .toList(); // 실패한 멤버 ID 들을 리스트로 수집
    }
}
