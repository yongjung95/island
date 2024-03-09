package com.tictoccroc.island.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Parent extends BaseTime {

    @Id
    private Long id;

    private String name;

    private String email;

    public static Parent createParent(Long id, String name, String email) {
        return Parent.builder()
                .id(id)
                .name(name)
                .email(email)
                .build();
    }
}
