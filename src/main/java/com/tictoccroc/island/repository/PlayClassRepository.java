package com.tictoccroc.island.repository;

import com.tictoccroc.island.model.PlayClass;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PlayClassRepository {

    private final EntityManager em;

    public void save(PlayClass playClass) {
        em.persist(playClass);
    }
}
