package com.fofo.core.storage;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class MatchCustomRepositoryImpl implements MatchCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;
    private final QMemberMatchEntity match = QMemberMatchEntity.memberMatchEntity;
    @Override
    public long deleteMatchesBy(List<Long> matchIdList, String hold) {
        return jpaQueryFactory.update(match)
                .set(match.status, "d")
                .where(match.id.in(matchIdList),
                        match.matchingStatus.eq(hold))
                .execute();
    }
}
