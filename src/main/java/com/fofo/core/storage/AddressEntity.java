package com.fofo.core.storage;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.storage.converter.ActiveStatusConverter;
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
import org.springframework.data.geo.Point;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Address")
public class AddressEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @Column(length = 5)
    private String zipCode;

    @Column(length = 20)
    private String sido;

    @Column(length = 20)
    private String sigungu;

    @Column(length = 20)
    private String eupmyundong;

    private Point location;

    @Column(nullable = false, length = 20)
    @Convert(converter = ActiveStatusConverter.class)
    private ActiveStatus status;

    private AddressEntity(final String zipCode,
                            final String sido,
                            final String sigungu,
                            final String eupmyundong,
                            final Point location,
                            final ActiveStatus status) {
        this.zipCode = zipCode;
        this.sido = sido;
        this.sigungu = sigungu;
        this.eupmyundong = eupmyundong;
        this.location = location;
        this.status = status;
    }

    public static AddressEntity of(final String zipcode,
                                   final String sido,
                                   final String sigungu,
                                   final String eupmyundong,
                                   final Point location,
                                   final ActiveStatus status) {
        return new AddressEntity(zipcode, sido, sigungu, eupmyundong, location, status);
    }

}
