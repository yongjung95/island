package com.tictoccroc.island.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayClass extends BaseTime {

    @Id
    private Long id;

    private String name;

    private Integer maxStudentCnt;

    public static PlayClass createPlayClass(Long id, String name, Integer maxStudentCnt) {
        return PlayClass.builder()
                .id(id)
                .name(name)
                .maxStudentCnt(maxStudentCnt)
                .build();
    }
}
