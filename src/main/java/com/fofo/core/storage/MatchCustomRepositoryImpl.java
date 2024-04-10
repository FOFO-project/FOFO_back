package com.fofo.core.storage;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.match.Match;
import com.fofo.core.domain.match.MatchingStatus;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class MatchCustomRepositoryImpl implements MatchCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;
    private final QMemberMatchEntity match = QMemberMatchEntity.memberMatchEntity;
    private final QMemberEntity manMember = QMemberEntity.memberEntity;
    private final QMemberEntity womanMember = QMemberEntity.memberEntity;
    @Override
    public long deleteMatchesBy(final List<Long> matchIdList) {
        long result = jpaQueryFactory.update(match)
                .set(match.status, ActiveStatus.DELETED)
                .set(match.updatedTime, LocalDateTime.now())
                .where(match.id.in(matchIdList))
                .execute();
        em.flush();
        em.clear();
        return result;
    }

    @Override
    public Page<Match> selectMatchResultList(final Pageable pageable) {
        List<Match> matchResultList = jpaQueryFactory
                .select(
                        Projections.bean(
                                Match.class,
                                match.id,
                                manMember,
                                womanMember,
                                match.matchingStatus
                        )
                )
                .from(match)
                .join(manMember).on(match.manMemberId.eq(manMember.id))
                .join(womanMember).on(match.womanMemberId.eq(womanMember.id))
                .where(
                        match.status.ne(ActiveStatus.DELETED),
                        manMember.status.ne(ActiveStatus.DELETED),
                        womanMember.status.ne(ActiveStatus.DELETED)
                )
                .orderBy(match.createdTime.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = jpaQueryFactory
                .select(match.count())
                .from(match)
                .where(
                        match.status.ne(ActiveStatus.DELETED),
                        manMember.status.ne(ActiveStatus.DELETED),
                        womanMember.status.ne(ActiveStatus.DELETED)
                )
                .fetchOne();
        return new PageImpl<>(matchResultList, pageable, count == null? 0 : count);
    }

    @Override
    public long updateMatchStatus(final List<Long> matchIdList, final MatchingStatus matchingStatus) {
        long result = jpaQueryFactory.update(match)
                        .set(match.matchingStatus, matchingStatus)
                        .set(match.updatedTime, LocalDateTime.now())
                        .where(match.id.in(matchIdList))
                        .execute();
        em.flush();
        em.clear();
        return result;
    }


}
