package com.tictoccroc.island.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApplyPlayClass extends BaseTime {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_playclass_id")
    private StorePlayClass storePlayClass;

    @Enumerated(EnumType.STRING)
    private ApplyStatus status; // APPLY[신청], CANCEL[취소]

    private Integer applyChildCnt;

    private LocalDate applyDate;

    public void changeApplyStatus(ApplyStatus applyStatus) {
        this.status = applyStatus;
    }
}
