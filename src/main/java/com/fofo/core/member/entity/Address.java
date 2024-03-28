package com.fofo.core.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

    private String zipCode;

    private String siDo;

    private String siGunGu;

    private String eupMyunDong;

    private String detail;

    private String roadNameCd;

    private Point location;

    private char status;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;
}
