package com.fofo.core.storage;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberImageEntity is a Querydsl query type for MemberImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberImageEntity extends EntityPathBase<MemberImageEntity> {

    private static final long serialVersionUID = 1543094257L;

    public static final QMemberImageEntity memberImageEntity = new QMemberImageEntity("memberImageEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdTime = _super.createdTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final StringPath status = createString("status");

    public final StringPath type = createString("type");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedTime = _super.updatedTime;

    public QMemberImageEntity(String variable) {
        super(MemberImageEntity.class, forVariable(variable));
    }

    public QMemberImageEntity(Path<? extends MemberImageEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberImageEntity(PathMetadata metadata) {
        super(MemberImageEntity.class, metadata);
    }

}

