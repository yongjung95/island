package com.tictoccroc.island.dto;

import com.tictoccroc.island.model.ApplyStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
public class ApplyPlayClassDto {

    @Getter
    @Setter
    public static class ApplyStorePlayClassRequestDto {
        @Min(1)
        @NotBlank
        private Long storePlayClassId;

        @Min(1)
        @NotBlank
        private Long parentId;

        @Min(1)
        @NotBlank
        private Integer applyChildCnt;

        @NotBlank
        private String applyDate;
    }

    @Getter
    @Setter
    public static class CancelStorePlayClassRequestDto {
        private Long applyPlayClassId;

        @Min(1)
        @NotBlank
        private Long parentId;
    }

    @Getter
    @Setter
    @ToString
    public static class ApplyStorePlayClassResponseDto {
        private Long applyPlayClassId;

        private Long parentId;

        private Long storePlayClassId;

        private ApplyStatus status;

        private Integer applyChildCnt;

        private LocalDate applyDate;
    }

    @Getter
    @Setter
    @ToString
    public static class StorePlayClassApplyParentListResponseDto {
        private Long applyPlayClassId;

        private String storeName;

        private String playClassName;

        private String parentName;

        private Integer applyChildCnt;

        private ApplyStatus status;

        private LocalDate applyDate;
    }
}
