package com.fofo.core.storage;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@Table(name = "Address")
public class AddressEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MemberEntity memberEntity;

    @Size(max=5)
    private String zipCode;

    @Size(max=20)
    private String sido;

    @Size(max=20)
    private String sigungu;

    @Size(max=20)
    private String eupmyundong;

    @Size(max=300)
    private String detail;

    @Size(max=12)
    private String roadNameCd;

    private Point location;

    @NotNull @Size(max=1)
    private String status;

}
