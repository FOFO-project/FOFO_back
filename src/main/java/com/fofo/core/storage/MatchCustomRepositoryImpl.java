package com.fofo.core.storage;

import com.fofo.core.domain.match.Match;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class MatchCustomRepositoryImpl implements MatchCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;
    private final QMemberMatchEntity match = QMemberMatchEntity.memberMatchEntity;
    private final QMemberEntity maleMember = QMemberEntity.memberEntity;
    private final QMemberEntity femaleMember = QMemberEntity.memberEntity;
    @Override
    public long deleteMatchesBy(List<Long> matchIdList, String hold) {
        return jpaQueryFactory.update(match)
                .set(match.status, "d")
                .where(match.id.in(matchIdList),
                        match.matchingStatus.eq(hold))
                .execute();
    }

    @Override
    public Page<Match> selectMatchResultList(Pageable pageable) {
        List<Match> matchResultList = jpaQueryFactory
                .select(
                        Projections.bean(
                                Match.class,
                                match.id,
                                maleMember,
                                femaleMember,
                                match.matchingStatus
                        )
                )
                .from(match)
                .join(maleMember).on(match.maleMemberId.eq(maleMember.id))
                .join(femaleMember).on(match.femaleMemberId.eq(femaleMember.id))
                .where(
                        match.status.ne("d"),
                        maleMember.status.ne("d"),
                        femaleMember.status.ne("d")
                )
                .orderBy(match.createdTime.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = jpaQueryFactory
                .select(match.count())
                .from(match)
                .where(
                        match.status.ne("d"),
                        maleMember.status.ne("d"),
                        femaleMember.status.ne("d")
                )
                .fetchOne();
        return new PageImpl<>(matchResultList, pageable, count == null? 0 : count);
    }


}
