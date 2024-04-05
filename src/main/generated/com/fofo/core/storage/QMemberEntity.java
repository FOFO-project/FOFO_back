package com.fofo.core.storage;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberEntity is a Querydsl query type for MemberEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberEntity extends EntityPathBase<MemberEntity> {

    private static final long serialVersionUID = -558516944L;

    public static final QMemberEntity memberEntity = new QMemberEntity("memberEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> addressId = createNumber("addressId", Long.class);

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final StringPath approvalStatus = createString("approvalStatus");

    public final DateTimePath<java.time.LocalDateTime> birthday = createDateTime("birthday", java.time.LocalDateTime.class);

    public final NumberPath<Integer> chance = createNumber("chance", Integer.class);

    public final StringPath charmingPoint = createString("charmingPoint");

    public final StringPath company = createString("company");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdTime = _super.createdTime;

    public final DateTimePath<java.time.LocalDateTime> depositDate = createDateTime("depositDate", java.time.LocalDateTime.class);

    public final StringPath filteringConditionAgeRelation = createString("filteringConditionAgeRelation");

    public final StringPath filteringConditionReligion = createString("filteringConditionReligion");

    public final BooleanPath filteringConditionSmokingYn = createBoolean("filteringConditionSmokingYn");

    public final StringPath gender = createString("gender");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath job = createString("job");

    public final StringPath kakaoId = createString("kakaoId");

    public final StringPath mbti = createString("mbti");

    public final StringPath name = createString("name");

    public final StringPath note = createString("note");

    public final NumberPath<Integer> passCount = createNumber("passCount", Integer.class);

    public final StringPath religion = createString("religion");

    public final BooleanPath smokingYn = createBoolean("smokingYn");

    public final StringPath status = createString("status");

    public final StringPath university = createString("university");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedTime = _super.updatedTime;

    public QMemberEntity(String variable) {
        super(MemberEntity.class, forVariable(variable));
    }

    public QMemberEntity(Path<? extends MemberEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberEntity(PathMetadata metadata) {
        super(MemberEntity.class, metadata);
    }

}

