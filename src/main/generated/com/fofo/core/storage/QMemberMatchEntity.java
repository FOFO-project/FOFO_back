package com.fofo.core.storage;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberMatchEntity is a Querydsl query type for MemberMatchEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberMatchEntity extends EntityPathBase<MemberMatchEntity> {

    private static final long serialVersionUID = 1012758747L;

    public static final QMemberMatchEntity memberMatchEntity = new QMemberMatchEntity("memberMatchEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdTime = _super.createdTime;

    public final NumberPath<Long> femaleMemberId = createNumber("femaleMemberId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> maleMemberId = createNumber("maleMemberId", Long.class);

    public final StringPath matchingStatus = createString("matchingStatus");

    public final StringPath status = createString("status");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedTime = _super.updatedTime;

    public QMemberMatchEntity(String variable) {
        super(MemberMatchEntity.class, forVariable(variable));
    }

    public QMemberMatchEntity(Path<? extends MemberMatchEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberMatchEntity(PathMetadata metadata) {
        super(MemberMatchEntity.class, metadata);
    }

}

