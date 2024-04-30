package com.fofo.core.storage;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.image.ImageType;
import com.fofo.core.storage.converter.ActiveStatusConverter;
import com.fofo.core.storage.converter.ImageTypeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member_image", indexes = {@Index(columnList = "memberId")})
public class MemberImageEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_image_id")
    private long id;

    @Column(nullable = false)
    private long memberId;

    @Convert(converter = ImageTypeConverter.class)
    @Column(nullable = false)
    private ImageType type;

    @Column(nullable = false)
    private String uploadFileName;

    @Column(nullable = false)
    private String storeFileName;

    @Column(nullable = false, length = 20)
    @Convert(converter = ActiveStatusConverter.class)
    private ActiveStatus status;

    private MemberImageEntity(final long memberId,
                              final ImageType type,
                              final String uploadFileName,
                              final String storeFileName,
                              final ActiveStatus status) {
        this.memberId = memberId;
        this.type = type;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.status = status;
    }

    public static MemberImageEntity of(final long memberId,
                                       final ImageType type,
                                       final String uploadFileName,
                                       final String storeFileName,
                                       final ActiveStatus status) {
        return new MemberImageEntity(memberId, type, uploadFileName, storeFileName, status);
    }
}
