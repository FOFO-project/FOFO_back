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
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "address")
public class AddressEntity extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @Column(length = 5)
    private String zipCode;

    @Column(length = 20)
    private String sido;

    @Column(length = 20)
    private String sigungu;

    @Column(nullable = false, length = 20)
    @Convert(converter = ActiveStatusConverter.class)
    private ActiveStatus status;

    private AddressEntity(final String zipCode,
                            final String sido,
                            final String sigungu,
                            final ActiveStatus status) {
        this.zipCode = zipCode;
        this.sido = sido;
        this.sigungu = sigungu;
        this.status = status;
    }

    public static AddressEntity of(final String zipcode,
                                   final String sido,
                                   final String sigungu,
                                   final ActiveStatus status) {
        return new AddressEntity(zipcode, sido, sigungu, status);
    }

}
