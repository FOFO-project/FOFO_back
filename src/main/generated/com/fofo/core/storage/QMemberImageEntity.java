package com.fofo.core.storage;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberImageEntity is a Querydsl query type for MemberImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberImageEntity extends EntityPathBase<MemberImageEntity> {

    private static final long serialVersionUID = 1543094257L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberImageEntity memberImageEntity = new QMemberImageEntity("memberImageEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final QMemberEntity memberEntity;

    public final StringPath status = createString("status");

    public final StringPath type = createString("type");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QMemberImageEntity(String variable) {
        this(MemberImageEntity.class, forVariable(variable), INITS);
    }

    public QMemberImageEntity(Path<? extends MemberImageEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberImageEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberImageEntity(PathMetadata metadata, PathInits inits) {
        this(MemberImageEntity.class, metadata, inits);
    }

    public QMemberImageEntity(Class<? extends MemberImageEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberEntity = inits.isInitialized("memberEntity") ? new QMemberEntity(forProperty("memberEntity"), inits.get("memberEntity")) : null;
    }

}

