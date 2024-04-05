package com.fofo.core.storage;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAddressEntity is a Querydsl query type for AddressEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAddressEntity extends EntityPathBase<AddressEntity> {

    private static final long serialVersionUID = 1840329028L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAddressEntity addressEntity = new QAddressEntity("addressEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    public final StringPath detail = createString("detail");

    public final StringPath eupmyundong = createString("eupmyundong");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ComparablePath<org.locationtech.jts.geom.Point> location = createComparable("location", org.locationtech.jts.geom.Point.class);

    public final QMemberEntity memberEntity;

    public final StringPath roadNameCd = createString("roadNameCd");

    public final StringPath sido = createString("sido");

    public final StringPath sigungu = createString("sigungu");

    public final StringPath status = createString("status");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public final StringPath zipCode = createString("zipCode");

    public QAddressEntity(String variable) {
        this(AddressEntity.class, forVariable(variable), INITS);
    }

    public QAddressEntity(Path<? extends AddressEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAddressEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAddressEntity(PathMetadata metadata, PathInits inits) {
        this(AddressEntity.class, metadata, inits);
    }

    public QAddressEntity(Class<? extends AddressEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberEntity = inits.isInitialized("memberEntity") ? new QMemberEntity(forProperty("memberEntity"), inits.get("memberEntity")) : null;
    }

}

