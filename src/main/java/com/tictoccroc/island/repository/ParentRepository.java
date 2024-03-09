package com.tictoccroc.island.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tictoccroc.island.model.Parent;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import static com.tictoccroc.island.model.QParent.parent;

@Repository
public class ParentRepository {

    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    public ParentRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Parent findOne(Long parentId) {
        return queryFactory
                .select(parent)
                .from(parent)
                .where(parent.id.eq(parentId))
                .fetchOne();
    }

    public void save(Parent parent) {
        em.persist(parent);
    }
}
