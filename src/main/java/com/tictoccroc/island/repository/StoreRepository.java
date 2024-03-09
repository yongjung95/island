package com.tictoccroc.island.repository;

import com.tictoccroc.island.model.Store;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StoreRepository {

    private final EntityManager em;

    public void save(Store store) {
        em.persist(store);
    }
}
