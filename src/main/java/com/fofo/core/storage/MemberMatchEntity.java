package com.fofo.core.storage;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.match.MatchingStatus;
import com.fofo.core.storage.converter.MatchingStatusConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
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

    @Column(nullable = false)
    @Convert(converter = MatchingStatusConverter.class)
    private MatchingStatus matchingStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActiveStatus status;

}
