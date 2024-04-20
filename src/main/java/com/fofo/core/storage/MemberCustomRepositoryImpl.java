package com.fofo.core.storage;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.member.AgeRelationType;
import com.fofo.core.domain.member.ApprovalStatus;
import com.fofo.core.domain.member.FilteringSmoker;
import com.fofo.core.domain.member.FindMember;
import com.fofo.core.domain.member.Gender;
import com.fofo.core.domain.member.Mbti;
import com.fofo.core.domain.member.Religion;
import com.fofo.core.domain.member.SmokingYn;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
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

    @Override
    public List<Tuple> findMembersWithCondition(final FindMember findMember, final Pageable pageable) {
        return jpaQueryFactory.select(Q_MEMBER, Q_ADDRESS)
                .from(Q_MEMBER)
                // fetch join 사용 -> 조회의 주체가 되는 Entity 이외에 fetch Join 이 걸린 연관 entity 도 함께 select 하여 모두 영속화
                .leftJoin(Q_ADDRESS).on(Q_MEMBER.addressId.eq(Q_ADDRESS.id)).fetchJoin()
                .where(
                        containKakaoId(findMember.kakaoId()),
                        containName(findMember.name()),
                        eqGender(findMember.gender()),
                        eqYearOfBirth(findMember.yearOfBirthday()),
                        eqFilteringConditionAgeRelation(findMember.filteringAgeRelation()),
                        containCompany(findMember.company()),
                        containJob(findMember.job()),
                        containUniversity(findMember.university()),
                        eqMbti(findMember.mbti()),
                        eqSmokingYn(findMember.smokingYn()),
                        eqFilteringSmoker(findMember.filteringSmoker()),
                        eqReligion(findMember.religion()),
                        eqFilteringReligion(findMember.filteringReligion()),
                        eqApprovalStatus(findMember.approvalStatus()),
                        Q_MEMBER.status.ne(ActiveStatus.DELETED)
                )
                .orderBy(Q_MEMBER.depositDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
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

    private BooleanExpression eqFilteringConditionAgeRelation(final AgeRelationType filteringConditionAgeRelation) {
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
}
