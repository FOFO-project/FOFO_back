package com.fofo.core.storage;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHashtagEntity is a Querydsl query type for HashtagEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHashtagEntity extends EntityPathBase<HashtagEntity> {

    private static final long serialVersionUID = -233042148L;

    public static final QHashtagEntity hashtagEntity = new QHashtagEntity("hashtagEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdTime = _super.createdTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedTime = _super.updatedTime;

    public QHashtagEntity(String variable) {
        super(HashtagEntity.class, forVariable(variable));
    }

    public QHashtagEntity(Path<? extends HashtagEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHashtagEntity(PathMetadata metadata) {
        super(HashtagEntity.class, metadata);
    }

}

