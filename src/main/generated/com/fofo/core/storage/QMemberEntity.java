package com.fofo.core.storage;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberEntity is a Querydsl query type for MemberEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberEntity extends EntityPathBase<MemberEntity> {

    private static final long serialVersionUID = -558516944L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberEntity memberEntity = new QMemberEntity("memberEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QAddressEntity addressEntity;

    public final StringPath approvalStatus = createString("approvalStatus");

    public final DateTimePath<java.time.LocalDateTime> birth = createDateTime("birth", java.time.LocalDateTime.class);

    public final NumberPath<Integer> chance = createNumber("chance", Integer.class);

    public final StringPath charmingPoint = createString("charmingPoint");

    public final StringPath company = createString("company");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    public final DateTimePath<java.sql.Timestamp> depositDate = createDateTime("depositDate", java.sql.Timestamp.class);

    public final ListPath<MemberMatchEntity, QMemberMatchEntity> femaleMemberMatchEntityList = this.<MemberMatchEntity, QMemberMatchEntity>createList("femaleMemberMatchEntityList", MemberMatchEntity.class, QMemberMatchEntity.class, PathInits.DIRECT2);

    public final StringPath filteringCondition = createString("filteringCondition");

    public final StringPath gender = createString("gender");

    public final ListPath<HashtagEntity, QHashtagEntity> hashTagEntities = this.<HashtagEntity, QHashtagEntity>createList("hashTagEntities", HashtagEntity.class, QHashtagEntity.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath job = createString("job");

    public final StringPath kakaoId = createString("kakaoId");

    public final ListPath<MemberMatchEntity, QMemberMatchEntity> maleMemberMatchEntityList = this.<MemberMatchEntity, QMemberMatchEntity>createList("maleMemberMatchEntityList", MemberMatchEntity.class, QMemberMatchEntity.class, PathInits.DIRECT2);

    public final StringPath mbti = createString("mbti");

    public final ListPath<MemberImageEntity, QMemberImageEntity> memberImageEntityList = this.<MemberImageEntity, QMemberImageEntity>createList("memberImageEntityList", MemberImageEntity.class, QMemberImageEntity.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath note = createString("note");

    public final NumberPath<Integer> passCount = createNumber("passCount", Integer.class);

    public final StringPath religion = createString("religion");

    public final BooleanPath smokingYn = createBoolean("smokingYn");

    public final StringPath status = createString("status");

    public final StringPath university = createString("university");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QMemberEntity(String variable) {
        this(MemberEntity.class, forVariable(variable), INITS);
    }

    public QMemberEntity(Path<? extends MemberEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberEntity(PathMetadata metadata, PathInits inits) {
        this(MemberEntity.class, metadata, inits);
    }

    public QMemberEntity(Class<? extends MemberEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.addressEntity = inits.isInitialized("addressEntity") ? new QAddressEntity(forProperty("addressEntity"), inits.get("addressEntity")) : null;
    }

}

