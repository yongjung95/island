package com.tictoccroc.island.web;

import com.tictoccroc.island.dto.ApplyPlayClassDto;
import com.tictoccroc.island.error.CustomException;
import com.tictoccroc.island.response.error.ErrorCode;
import com.tictoccroc.island.response.model.ListResult;
import com.tictoccroc.island.response.model.SingleResult;
import com.tictoccroc.island.response.service.ResponseService;
import com.tictoccroc.island.service.ApplyPlayClassService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ApplyStorePlayClass", description = "째깍섬 예약 관련 API")
@RestController
@RequiredArgsConstructor
public class ApplyPlayClassController {

    private final ApplyPlayClassService applyPlayClassService;

    private final ResponseService responseService;

    @Operation(
            summary = "매장별, 수업별 예약 API",
            description = "부모의 테이블의 PK 값과 매장별 수업 테이블의 PK 값을 이용하여 예약합니다."
    )
    @PostMapping("/api/play-class")
    public SingleResult<?> applyPlayClass(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(
                    examples = {
                            @ExampleObject(name = "applyExample", value = """ 
                                        {
                                            "storePlayClassId": 1,
                                            "parentId": 1,
                                            "applyChildCnt": 1,
                                            "applyDate": "2024-03-22"
                                        }
                                    """)
                    }
            ))
            @Valid @RequestBody ApplyPlayClassDto.ApplyStorePlayClassRequestDto applyStorePlayClassRequestDto) {

        if (applyStorePlayClassRequestDto == null) {
            return responseService.getFailResult(ErrorCode.PARAMETER_IS_EMPTY);
        }

        try {
            return responseService.getSingleResult(applyPlayClassService.applyStorePlayClass(applyStorePlayClassRequestDto));
        } catch (CustomException e) {
            return responseService.getFailResult(e.getErrorCode());
        }
    }

    @Operation(
            summary = "매장별, 수업별 예약 취소 API",
            description = "부모의 테이블의 PK 값과 매장별 수업 신청테이블의 PK 값을 이용하여 취소합니다."
    )
    @DeleteMapping("/api/play-class/{applyPlayClassId}")
    public SingleResult<?> cancelPlayClass(
            @Parameter(description = "매장별 수업 신청 ID", example = "1")
            @PathVariable(value = "applyPlayClassId") Long applyPlayClassId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(
                    examples = {
                            @ExampleObject(name = "cancelExample", value = """ 
                                        {
                                            "parentId": 2
                                        }
                                    """)
                    }
            ))
            @Valid @RequestBody ApplyPlayClassDto.CancelStorePlayClassRequestDto cancelStorePlayClassRequestDto) {

        if (applyPlayClassId == null || cancelStorePlayClassRequestDto == null) {
            return responseService.getFailResult(ErrorCode.PARAMETER_IS_EMPTY);
        }

        try {
            cancelStorePlayClassRequestDto.setApplyPlayClassId(applyPlayClassId);
            return responseService.getSingleResult(applyPlayClassService.cancelStorePlayClass(cancelStorePlayClassRequestDto));
        } catch (CustomException e) {
            return responseService.getFailResult(e.getErrorCode());
        }
    }

    @Operation(
            summary = "매장별, 수업별 예약자 현황 API",
            description = "매장별 수업 테이블의 PK 값을 이용하여 예약자를 조회합니다."
    )
    @GetMapping("/api/store-play-class/parent/{storeClassId}")
    public ListResult<?> applyStorePlayClassParentList(
            @Parameter(description = "매장별 수업 ID", example = "1")
            @PathVariable(value = "storeClassId") Long storeClassId) {

        if (storeClassId == null) {
            return responseService.getFailListResult(ErrorCode.PARAMETER_IS_EMPTY);
        }

        try {
            return responseService.getListResult(applyPlayClassService.findStorePlayClassApplyParentList(storeClassId));
        } catch (CustomException e) {
            return responseService.getFailListResult(e.getErrorCode());
        }

    }

    @Operation(
            summary = "매장별, 수업별 예약 이력 현황 API",
            description = "매장별 수업 테이블의 PK 값을 이용하여 모든 예약 이력을 조회합니다."
    )
    @GetMapping("/api/store-play-class/{storeClassId}")
    public ListResult<?> applyStorePlayClassList(
            @Parameter(description = "매장별 수업 ID", example = "1")
            @PathVariable(value = "storeClassId") Long storeClassId) {

        if (storeClassId == null) {
            return responseService.getFailListResult(ErrorCode.PARAMETER_IS_EMPTY);
        }

        try {
            return responseService.getListResult(applyPlayClassService.findStorePlayClassAllParentList(storeClassId));
        } catch (CustomException e) {
            return responseService.getFailListResult(e.getErrorCode());
        }

    }
}
