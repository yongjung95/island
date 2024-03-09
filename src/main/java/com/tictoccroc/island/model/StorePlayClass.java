package com.tictoccroc.island.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StorePlayClass extends BaseTime {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "play_class_id")
    private PlayClass playClass;

    public static StorePlayClass createStorePlayClass(Long id, Store store, PlayClass playClass) {
        return StorePlayClass.builder()
                .id(id)
                .store(store)
                .playClass(playClass)
                .build();
    }
}
