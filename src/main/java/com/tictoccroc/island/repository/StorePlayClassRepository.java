package com.tictoccroc.island.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tictoccroc.island.model.StorePlayClass;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import static com.tictoccroc.island.model.QPlayClass.playClass;
import static com.tictoccroc.island.model.QStorePlayClass.storePlayClass;

@Repository
public class StorePlayClassRepository {

    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    public StorePlayClassRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public StorePlayClass findOne(Long id) {
        return queryFactory
                .select(storePlayClass)
                .from(storePlayClass)
                .join(storePlayClass.playClass, playClass).fetchJoin()
                .where(storePlayClass.id.eq(id))
                .fetchOne();
    }

    public void save(StorePlayClass storePlayClass) {
        em.persist(storePlayClass);
    }
}
