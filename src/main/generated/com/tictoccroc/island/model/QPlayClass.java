package com.tictoccroc.island.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlayClass is a Querydsl query type for PlayClass
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlayClass extends EntityPathBase<PlayClass> {

    private static final long serialVersionUID = 1297529994L;

    public static final QPlayClass playClass = new QPlayClass("playClass");

    public final QBaseTime _super = new QBaseTime(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> maxStudentCnt = createNumber("maxStudentCnt", Integer.class);

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QPlayClass(String variable) {
        super(PlayClass.class, forVariable(variable));
    }

    public QPlayClass(Path<? extends PlayClass> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlayClass(PathMetadata metadata) {
        super(PlayClass.class, metadata);
    }

}

