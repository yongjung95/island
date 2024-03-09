package com.tictoccroc.island.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Store extends BaseTime {

    @Id
    private Long id;

    private String name;

    private String address;

    public static Store createStore(Long id, String name, String address) {
        return Store.builder()
                .id(id)
                .name(name)
                .address(address)
                .build();
    }
}
