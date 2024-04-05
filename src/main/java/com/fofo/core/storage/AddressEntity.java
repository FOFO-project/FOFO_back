package com.fofo.core.storage;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Address")
public class AddressEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @Size(max = 5)
    private String zipCode;

    @Size(max = 20)
    private String sido;

    @Size(max = 20)
    private String sigungu;

    @Size(max = 20)
    private String eupmyundong;

    @Size(max = 300)
    private String detail;

    @Size(max = 12)
    private String roadNameCd;

    private Point location;

    @NotNull
    @Size(max = 1)
    private String status;

    private AddressEntity(final String zipCode,
                            final String sido,
                            final String sigungu,
                            final String eupmyundong,
                            final String detail,
                            final String roadNameCd,
                            final Point location,
                            final String status) {
        this.zipCode = zipCode;
        this.sido = sido;
        this.sigungu = sigungu;
        this.eupmyundong = eupmyundong;
        this.detail = detail;
        this.roadNameCd = roadNameCd;
        this.location = location;
        this.status = status;
    }

    public static AddressEntity of(final String zipcode,
                                   final String sido,
                                   final String sigungu,
                                   final String eupmyundong,
                                   final String detail,
                                   final String roadNameCd,
                                   final Point location,
                                   final String status) {
        return new AddressEntity(zipcode, sido, sigungu, eupmyundong, detail, roadNameCd, location, status);
    }

}
