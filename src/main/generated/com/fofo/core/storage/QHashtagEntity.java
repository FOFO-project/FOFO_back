package com.fofo.core.storage;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHashtagEntity is a Querydsl query type for HashtagEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHashtagEntity extends EntityPathBase<HashtagEntity> {

    private static final long serialVersionUID = -233042148L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHashtagEntity hashtagEntity = new QHashtagEntity("hashtagEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMemberEntity memberEntity;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QHashtagEntity(String variable) {
        this(HashtagEntity.class, forVariable(variable), INITS);
    }

    public QHashtagEntity(Path<? extends HashtagEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHashtagEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHashtagEntity(PathMetadata metadata, PathInits inits) {
        this(HashtagEntity.class, metadata, inits);
    }

    public QHashtagEntity(Class<? extends HashtagEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberEntity = inits.isInitialized("memberEntity") ? new QMemberEntity(forProperty("memberEntity"), inits.get("memberEntity")) : null;
    }

}

