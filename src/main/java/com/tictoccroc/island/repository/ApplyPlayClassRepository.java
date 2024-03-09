package com.tictoccroc.island.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tictoccroc.island.model.ApplyPlayClass;
import com.tictoccroc.island.model.ApplyStatus;
import com.tictoccroc.island.model.QStorePlayClass;
import com.tictoccroc.island.model.StorePlayClass;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.tictoccroc.island.model.QApplyPlayClass.applyPlayClass;
import static com.tictoccroc.island.model.QParent.parent;
import static com.tictoccroc.island.model.QPlayClass.playClass;
import static com.tictoccroc.island.model.QStore.store;
import static com.tictoccroc.island.model.QStorePlayClass.storePlayClass;

@Repository
public class ApplyPlayClassRepository {

    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    public ApplyPlayClassRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public void apply(ApplyPlayClass applyPlayClass) {
        em.persist(applyPlayClass);
    }

    public ApplyPlayClass findByApplyPlayClassIdAndParentId(Long applyPlayClassId, Long parentId) {
        return queryFactory
                .select(applyPlayClass)
                .from(applyPlayClass)
                .where(applyPlayClass.id.eq(applyPlayClassId).and(applyPlayClass.parent.id.eq(parentId)))
                .fetchOne();
    }

    public ApplyPlayClass findByStorePlayClassIdAndParentId(Long storePlayClassId, Long parentId) {
        return queryFactory
                .select(applyPlayClass)
                .from(applyPlayClass)
                .where(applyPlayClass.storePlayClass.id.eq(storePlayClassId)
                        .and(applyPlayClass.parent.id.eq(parentId))
                        .and(applyPlayClass.status.eq(ApplyStatus.APPLY)))
                .fetchOne();
    }

    public Long parentPlayClassCnt(StorePlayClass storePlayClass) {
        return queryFactory
                .select(applyPlayClass.count())
                .from(applyPlayClass)
                .join(applyPlayClass.storePlayClass, QStorePlayClass.storePlayClass)
                .where(QStorePlayClass.storePlayClass.eq(storePlayClass).and(applyPlayClass.status.eq(ApplyStatus.APPLY)))
                .fetchOne();
    }

    public List<ApplyPlayClass> findStorePlayClassApplyParentList(Long storePlayClassId) {

        return queryFactory
                .select(applyPlayClass)
                .from(applyPlayClass)
                .join(applyPlayClass.storePlayClass, storePlayClass).fetchJoin()
                .join(applyPlayClass.parent, parent).fetchJoin()
                .join(storePlayClass.store, store).fetchJoin()
                .join(storePlayClass.playClass, playClass).fetchJoin()
                .where(storePlayClass.id.eq(storePlayClassId)
                        .and(applyPlayClass.status.eq(ApplyStatus.APPLY)))
                .fetch();
    }

    public List<ApplyPlayClass> findStorePlayClassAllParentList(Long storePlayClassId) {
        return queryFactory
                .select(applyPlayClass)
                .from(applyPlayClass)
                .join(applyPlayClass.storePlayClass, storePlayClass).fetchJoin()
                .join(applyPlayClass.parent, parent).fetchJoin()
                .join(storePlayClass.store, store).fetchJoin()
                .join(storePlayClass.playClass, playClass).fetchJoin()
                .where(storePlayClass.id.eq(storePlayClassId))
                .fetch();
    }

}
