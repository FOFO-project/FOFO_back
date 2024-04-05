package com.fofo.core.storage;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberMatchEntity is a Querydsl query type for MemberMatchEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberMatchEntity extends EntityPathBase<MemberMatchEntity> {

    private static final long serialVersionUID = 1012758747L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberMatchEntity memberMatchEntity = new QMemberMatchEntity("memberMatchEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    public final QMemberEntity female;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMemberEntity male;

    public final StringPath matchingStatus = createString("matchingStatus");

    public final StringPath status = createString("status");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QMemberMatchEntity(String variable) {
        this(MemberMatchEntity.class, forVariable(variable), INITS);
    }

    public QMemberMatchEntity(Path<? extends MemberMatchEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberMatchEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberMatchEntity(PathMetadata metadata, PathInits inits) {
        this(MemberMatchEntity.class, metadata, inits);
    }

    public QMemberMatchEntity(Class<? extends MemberMatchEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.female = inits.isInitialized("female") ? new QMemberEntity(forProperty("female"), inits.get("female")) : null;
        this.male = inits.isInitialized("male") ? new QMemberEntity(forProperty("male"), inits.get("male")) : null;
    }

}

