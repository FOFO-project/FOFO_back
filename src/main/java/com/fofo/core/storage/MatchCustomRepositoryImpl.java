package com.fofo.core.storage;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.match.MatchingStatus;
import com.fofo.core.domain.member.ApprovalStatus;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MatchCustomRepositoryImpl implements MatchCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;
    private final QMemberMatchEntity match = QMemberMatchEntity.memberMatchEntity;
    private final QMemberEntity member = QMemberEntity.memberEntity;
    private final QMemberEntity manMember = new QMemberEntity("manMember");
    private final QMemberEntity womanMember = new QMemberEntity("womanMember");
    private final QAddressEntity manAddress = new QAddressEntity("manAddress");
    private final QAddressEntity womanAddress = new QAddressEntity("womanAddress");

    @Override
    public Optional<MemberEntity> findMatchPossibleMemberById(final Long id) {
        return Optional.ofNullable(jpaQueryFactory.select(member)
                .from(member)
                .leftJoin(match).on(member.id.eq(match.manMemberId).or(member.id.eq(match.womanMemberId)))
                .where(
                        member.id.eq(id),
                        member.approvalStatus.eq(ApprovalStatus.APPROVED), //멤버 승인상태
                        member.status.ne(ActiveStatus.DELETED), //멤버 삭제되지 않은 상태
                        matchPossible()
                )
                .fetchOne());
    }

    @Override
    public List<MemberEntity> findMatchPossibleMembers() {
        return jpaQueryFactory.select(member)
                .from(member)
                .leftJoin(match).on(member.id.eq(match.manMemberId).or(member.id.eq(match.womanMemberId)))
                .where(
                        member.approvalStatus.eq(ApprovalStatus.APPROVED), //멤버 승인상태
                        member.status.ne(ActiveStatus.DELETED), //멤버 삭제되지 않은 상태
                        matchPossible()
                )
                .orderBy(member.depositDate.asc())
                .fetch();
    }

    private BooleanExpression matchPossible() {
        return match.status.ne(ActiveStatus.DELETED).and(match.matchingStatus.eq(MatchingStatus.MATCHING_COMPLETED)) //매치 완료 상태
                .or(match.status.eq(ActiveStatus.DELETED)) // 매치 삭제된 상태
                .or(match.isNull()); //매치 진행 된 적 없는 상태
    }


    @Override
    public Pair<List<Tuple>, Long> findMatchResultList(final Pageable pageable, final MatchingStatus matchingStatus) {
        List<Tuple> matchResultTupleList = jpaQueryFactory
                .select(match, manMember, womanMember, manAddress, womanAddress)
                .from(match)
                .leftJoin(manMember).on(match.manMemberId.eq(manMember.id))
                .leftJoin(womanMember).on(match.womanMemberId.eq(womanMember.id))
                .leftJoin(manAddress).on(manMember.addressId.eq(manAddress.id))
                .leftJoin(womanAddress).on(womanMember.addressId.eq(womanAddress.id))
                .where(
                        match.status.ne(ActiveStatus.DELETED),
                        eqMatchingStatus(matchingStatus),
                        manMember.status.ne(ActiveStatus.DELETED),
                        womanMember.status.ne(ActiveStatus.DELETED)
                )
                .orderBy(match.createdTime.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = jpaQueryFactory
                .select(match.count())
                .from(match)
                .join(manMember).on(match.manMemberId.eq(manMember.id))
                .join(womanMember).on(match.womanMemberId.eq(womanMember.id))
                .where(
                        match.status.ne(ActiveStatus.DELETED),
                        eqMatchingStatus(matchingStatus),
                        manMember.status.ne(ActiveStatus.DELETED),
                        womanMember.status.ne(ActiveStatus.DELETED)
                )
                .fetchOne();

        return Pair.of(matchResultTupleList, count);
    }

    private BooleanExpression eqMatchingStatus(final MatchingStatus matchingStatus) {
        return matchingStatus != null ? match.matchingStatus.eq(matchingStatus) : null;
    }

}
