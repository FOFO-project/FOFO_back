package com.fofo.core.storage;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.match.MatchingStatus;
import com.fofo.core.domain.member.AgeRelationType;
import com.fofo.core.domain.member.ApprovalStatus;
import com.fofo.core.domain.member.FilteringSmoker;
import com.fofo.core.domain.member.FindMember;
import com.fofo.core.domain.member.Gender;
import com.fofo.core.domain.member.MatchableYn;
import com.fofo.core.domain.member.Mbti;
import com.fofo.core.domain.member.Religion;
import com.fofo.core.domain.member.SmokingYn;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    private static final QMemberEntity Q_MEMBER = QMemberEntity.memberEntity;
    private static final QAddressEntity Q_ADDRESS = QAddressEntity.addressEntity;
    private static final QMemberMatchEntity Q_MEMBER_MATCH = QMemberMatchEntity.memberMatchEntity;

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Tuple findMemberById(final long memberId) {
        return jpaQueryFactory.select(Q_MEMBER, Q_ADDRESS)
                .from(Q_MEMBER)
                // fetch join 사용 -> 조회의 주체가 되는 Entity 이외에 fetch Join 이 걸린 연관 entity 도 함께 select 하여 모두 영속화
                .leftJoin(Q_ADDRESS).on(Q_MEMBER.addressId.eq(Q_ADDRESS.id)).fetchJoin()
                .where(
                        Q_MEMBER.id.eq(memberId),
                        Q_MEMBER.status.ne(ActiveStatus.DELETED)
                )
                .fetchOne();
    }

    public Long countMembersWithCondition(final FindMember findMember) {
        return jpaQueryFactory.select(Q_MEMBER.count())
                .from(Q_MEMBER)
                .leftJoin(Q_ADDRESS)
                .on(Q_MEMBER.addressId.eq(Q_ADDRESS.id), Q_MEMBER.status.ne(ActiveStatus.DELETED))
                .where(getPredicates(findMember))
                .fetchOne();
    }

    @Override
    public List<Tuple> findMembersWithCondition(final FindMember findMember, final Pageable pageable) {
        return jpaQueryFactory.select(Q_MEMBER, Q_ADDRESS)
                .from(Q_MEMBER)
                .leftJoin(Q_ADDRESS)
                .on(Q_MEMBER.addressId.eq(Q_ADDRESS.id))
                .where(getPredicates(findMember), Q_MEMBER.status.ne(ActiveStatus.DELETED))
                .orderBy(Q_MEMBER.depositDate.asc(),
                        Q_MEMBER.createdTime.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private BooleanExpression getPredicates(final FindMember findMember) {
        BooleanExpression expression;

        // 카카오 ID 포함 여부
        expression = containKakaoId(findMember.kakaoId());
        // 이름 포함 여부
        expression = combineExpression(expression, containName(findMember.name()));
        // 성별 일치 여부
        expression = combineExpression(expression, eqGender(findMember.gender()));
        // 생년월일 일치 여부
        expression = combineExpression(expression, eqYearOfBirth(findMember.yearOfBirthday()));
        // 키 일치 여부
        expression = combineExpression(expression, eqHeight(findMember.height()));
        // 연령 필터링 조건 일치 여부
        expression = combineExpression(expression, eqFilteringAgeRelation(findMember.filteringAgeRelation()));
        // 회사명 포함 여부
        expression = combineExpression(expression, containCompany(findMember.company()));
        // 직업 포함 여부
        expression = combineExpression(expression, containJob(findMember.job()));
        // 대학교 포함 여부
        expression = combineExpression(expression, containUniversity(findMember.university()));
        // MBTI 일치 여부
        expression = combineExpression(expression, eqMbti(findMember.mbti()));
        // 흡연 여부 일치 여부
        expression = combineExpression(expression, eqSmokingYn(findMember.smokingYn()));
        // 흡연 필터링 조건 일치 여부
        expression = combineExpression(expression, eqFilteringSmoker(findMember.filteringSmoker()));
        // 종교 일치 여부
        expression = combineExpression(expression, eqReligion(findMember.religion()));
        // 종교 필터링 조건 일치 여부
        expression = combineExpression(expression, eqFilteringReligion(findMember.filteringReligion()));
        // 승인 상태 일치 여부
        expression = combineExpression(expression, eqApprovalStatus(findMember.approvalStatus()));
        // 매칭 가능 여부 일치 여부
        expression = combineExpression(expression, eqMatchableYn(findMember.matchableYn()));
        // 시도 포함 여부
        expression = combineExpression(expression, containSido(findMember.sido()));
        // 시군구 포함 여부
        expression = combineExpression(expression, containSigungu(findMember.sigungu()));
        // 매칭 상태 일치 여부
        expression = combineExpression(expression, eqMemberMatch(findMember.matchingStatus()));

        return expression;
    }

    private BooleanExpression combineExpression(BooleanExpression expression, BooleanExpression newExpression) {
        if (newExpression == null) {
            return expression;
        }
        if (expression == null) {
            return newExpression;
        }
        return expression.and(newExpression);
    }

    private BooleanExpression containKakaoId(final String kakaoId) {
        if (StringUtils.isEmpty(kakaoId)) return null;
        return Q_MEMBER.kakaoId.contains(kakaoId);
    }

    private BooleanExpression containName(final String name) {
        if (StringUtils.isEmpty(name)) return null;
        return Q_MEMBER.name.contains(name);
    }

    private BooleanExpression eqGender(final Gender gender) {
        if (gender == null) return null;
        return Q_MEMBER.gender.eq(gender);
    }

    private BooleanExpression eqYearOfBirth(final LocalDate yearOfBirth) {
        if (yearOfBirth == null) return null;
        return Q_MEMBER.birthday.year().eq(yearOfBirth.getYear());
    }

    private BooleanExpression eqHeight(final Integer height) {
        if (height == null) return null;
        return Q_MEMBER.height.eq(height);
    }

    private BooleanExpression eqFilteringAgeRelation(final AgeRelationType filteringConditionAgeRelation) {
        if (filteringConditionAgeRelation == null) return null;
        return Q_MEMBER.filteringAgeRelation.eq(filteringConditionAgeRelation);
    }

    private BooleanExpression containCompany(final String company) {
        if (StringUtils.isEmpty(company)) return null;
        return Q_MEMBER.company.contains(company);
    }

    private BooleanExpression containJob(final String job) {
        if (StringUtils.isEmpty(job)) return null;
        return Q_MEMBER.job.contains(job);
    }

    private BooleanExpression containUniversity(final String university) {
        if (StringUtils.isEmpty(university)) return null;
        return Q_MEMBER.university.contains(university);
    }

    private BooleanExpression eqMbti(final Mbti mbti) {
        if (mbti == null) return null;
        return Q_MEMBER.mbti.eq(mbti);
    }

    private BooleanExpression eqSmokingYn(final SmokingYn smokingYn) {
        if (smokingYn == null) return null;
        return Q_MEMBER.smokingYn.eq(smokingYn);
    }

    private BooleanExpression eqFilteringSmoker(final FilteringSmoker filteringSmoker) {
        if (filteringSmoker == null) return null;
        return Q_MEMBER.filteringSmoker.eq(filteringSmoker);
    }

    private BooleanExpression eqReligion(final Religion religion) {
        if (religion == null) return null;
        return Q_MEMBER.religion.eq(religion);
    }

    private BooleanExpression eqFilteringReligion(final Religion filteringReligion) {
        if (filteringReligion == null) return null;
        return Q_MEMBER.filteringReligion.eq(filteringReligion);
    }

    private BooleanExpression eqApprovalStatus(final ApprovalStatus approvalStatus) {
        if (approvalStatus == null) return null;
        return Q_MEMBER.approvalStatus.eq(approvalStatus);
    }

    private BooleanExpression eqMatchableYn(final MatchableYn matchableYn) {
        if (matchableYn == null) return null;
        return Q_MEMBER.matchableYn.eq(matchableYn);
    }

    private BooleanExpression containSido(final String sido) {
        if (StringUtils.isEmpty(sido)) return null;
        return Q_ADDRESS.sido.contains(sido);
    }

    private BooleanExpression containSigungu(final String sigungu) {
        if (StringUtils.isEmpty(sigungu)) return null;
        return Q_ADDRESS.sigungu.contains(sigungu);
    }

    private BooleanExpression eqMemberMatch(final MatchingStatus matchingStatus) {
        if (matchingStatus == null) return null;
        return JPAExpressions.selectOne()
                .from(Q_MEMBER_MATCH)
                .where(
                        (Q_MEMBER_MATCH.manMemberId.eq(Q_MEMBER.id).or(Q_MEMBER_MATCH.womanMemberId.eq(Q_MEMBER.id)))
                        .and(Q_MEMBER_MATCH.matchingStatus.eq(matchingStatus))
                )
                .exists();
    }
}
