package com.tictoccroc.island.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStorePlayClass is a Querydsl query type for StorePlayClass
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStorePlayClass extends EntityPathBase<StorePlayClass> {

    private static final long serialVersionUID = -855329635L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStorePlayClass storePlayClass = new QStorePlayClass("storePlayClass");

    public final QBaseTime _super = new QBaseTime(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPlayClass playClass;

    public final QStore store;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QStorePlayClass(String variable) {
        this(StorePlayClass.class, forVariable(variable), INITS);
    }

    public QStorePlayClass(Path<? extends StorePlayClass> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStorePlayClass(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStorePlayClass(PathMetadata metadata, PathInits inits) {
        this(StorePlayClass.class, metadata, inits);
    }

    public QStorePlayClass(Class<? extends StorePlayClass> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.playClass = inits.isInitialized("playClass") ? new QPlayClass(forProperty("playClass")) : null;
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store")) : null;
    }

}

