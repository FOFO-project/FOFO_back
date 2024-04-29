package com.fofo.core.storage;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.match.MatchingStatus;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MatchCustomRepositoryImpl implements MatchCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;
    private static final QMemberMatchEntity match = QMemberMatchEntity.memberMatchEntity;
    private final QMemberEntity manMember = new QMemberEntity("manMember");
    private final QMemberEntity womanMember = new QMemberEntity("womanMember");
    private final QAddressEntity manAddress = new QAddressEntity("manAddress");
    private final QAddressEntity womanAddress = new QAddressEntity("womanAddress");

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
        if (matchingStatus == null){
            return match.matchingStatus.eq(MatchingStatus.MATCHING_PENDING).or(match.matchingStatus.eq(MatchingStatus.MATCHING_PROGRESSING));
        }
        return match.matchingStatus.eq(matchingStatus);
    }

}
