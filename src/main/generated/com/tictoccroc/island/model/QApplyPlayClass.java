package com.tictoccroc.island.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QApplyPlayClass is a Querydsl query type for ApplyPlayClass
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApplyPlayClass extends EntityPathBase<ApplyPlayClass> {

    private static final long serialVersionUID = -781010768L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApplyPlayClass applyPlayClass = new QApplyPlayClass("applyPlayClass");

    public final QBaseTime _super = new QBaseTime(this);

    public final NumberPath<Integer> applyChildCnt = createNumber("applyChildCnt", Integer.class);

    public final DatePath<java.time.LocalDate> applyDate = createDate("applyDate", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QParent parent;

    public final EnumPath<ApplyStatus> status = createEnum("status", ApplyStatus.class);

    public final QStorePlayClass storePlayClass;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QApplyPlayClass(String variable) {
        this(ApplyPlayClass.class, forVariable(variable), INITS);
    }

    public QApplyPlayClass(Path<? extends ApplyPlayClass> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QApplyPlayClass(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QApplyPlayClass(PathMetadata metadata, PathInits inits) {
        this(ApplyPlayClass.class, metadata, inits);
    }

    public QApplyPlayClass(Class<? extends ApplyPlayClass> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QParent(forProperty("parent")) : null;
        this.storePlayClass = inits.isInitialized("storePlayClass") ? new QStorePlayClass(forProperty("storePlayClass"), inits.get("storePlayClass")) : null;
    }

}

