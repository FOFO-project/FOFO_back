package com.fofo.core.storage;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.match.MatchAgreement;
import com.fofo.core.domain.match.MatchingStatus;
import com.fofo.core.storage.converter.ActiveStatusConverter;
import com.fofo.core.storage.converter.MatchAgreementConverter;
import com.fofo.core.storage.converter.MatchingStatusConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBER_MATCH")
public class MemberMatchEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long id;

    @Column(nullable = false)
    private Long manMemberId;

    @Column(nullable = false)
    private Long womanMemberId;

    @Column
    @Convert(converter = MatchAgreementConverter.class)
    private MatchAgreement manAgreement;

    @Column
    @Convert(converter = MatchAgreementConverter.class)
    private MatchAgreement womanAgreement;

    @Setter
    @Column(nullable = false)
    @Convert(converter = MatchingStatusConverter.class)
    private MatchingStatus matchingStatus;

    @Setter
    @Column(nullable = false, length = 2)
    @Convert(converter = ActiveStatusConverter.class)
    private ActiveStatus status;

    private MemberMatchEntity(
            final Long manMemberId,
            final Long womanMemberId,
            final MatchAgreement manAgreement,
            final MatchAgreement womanAgreement,
            final MatchingStatus matchingStatus,
            final ActiveStatus status
    ){
        this.manMemberId = manMemberId;
        this.womanMemberId = womanMemberId;
        this.manAgreement = manAgreement;
        this.womanAgreement = womanAgreement;
        this.matchingStatus = matchingStatus;
        this.status = status;
    }

    public static MemberMatchEntity of(
            final Long manMemberId,
            final Long womanMemberId,
            final MatchAgreement manAgreement,
            final MatchAgreement womanAgreement,
            final MatchingStatus matchingStatus,
            final ActiveStatus status) {
        return new MemberMatchEntity(
                manMemberId,
                womanMemberId,
                manAgreement,
                womanAgreement,
                matchingStatus,
                status
        );
    }
}
