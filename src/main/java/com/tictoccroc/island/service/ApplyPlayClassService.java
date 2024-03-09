package com.tictoccroc.island.service;

import com.tictoccroc.island.dto.ApplyPlayClassDto;
import com.tictoccroc.island.error.*;
import com.tictoccroc.island.mapper.ApplyPlayClassMapper;
import com.tictoccroc.island.model.ApplyPlayClass;
import com.tictoccroc.island.model.ApplyStatus;
import com.tictoccroc.island.model.Parent;
import com.tictoccroc.island.model.StorePlayClass;
import com.tictoccroc.island.repository.ApplyPlayClassRepository;
import com.tictoccroc.island.repository.ParentRepository;
import com.tictoccroc.island.repository.StorePlayClassRepository;
import com.tictoccroc.island.response.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ApplyPlayClassService {

    private final StorePlayClassRepository storePlayClassRepository;

    private final ApplyPlayClassRepository applyPlayClassRepository;

    private final ParentRepository parentRepository;

    public ApplyPlayClassDto.ApplyStorePlayClassResponseDto applyStorePlayClass(ApplyPlayClassDto.ApplyStorePlayClassRequestDto applyStorePlayClassRequestDto) {
        Parent parent = parentRepository.findOne(applyStorePlayClassRequestDto.getParentId());
        if (parent == null) {
            throw new NotFoundParentException("해당 부모님을 찾을 수 없습니다.", ErrorCode.NOT_FOUND_PARENT);
        }

        StorePlayClass storePlayClass = storePlayClassRepository.findOne(applyStorePlayClassRequestDto.getStorePlayClassId());
        if (storePlayClass == null) {
            throw new NotFoundStorePlayClassException("해당 매장의 수업을 찾을 수 없습니다.", ErrorCode.NOT_FOUND_STORE_PLAY_CLASS);
        }

        LocalDate applyDate = LocalDate.parse(applyStorePlayClassRequestDto.getApplyDate(), DateTimeFormatter.ISO_DATE);


        ApplyPlayClass findApplyPlayClass = applyPlayClassRepository.findByStorePlayClassIdAndParentId(applyStorePlayClassRequestDto.getStorePlayClassId(),
                applyStorePlayClassRequestDto.getParentId());
        if (findApplyPlayClass != null) {
            throw new DuplicateApplyPlayClassException("동일인이 동일매장, 동일수업에 중복하여 예약할 수 없습니다.", ErrorCode.DUPLICATE_APPLY_PLAY_CLASS);
        }

        Long parentPlayClassCnt = applyPlayClassRepository.parentPlayClassCnt(storePlayClass);
        if (storePlayClass.getPlayClass().getMaxStudentCnt() < (parentPlayClassCnt + applyStorePlayClassRequestDto.getApplyChildCnt())) {
            throw new ApplyMaxCntException("최대 정원 수 초과입니다.", ErrorCode.PLAY_CLASS_IS_FULL);
        }

        if (!isWithinRange(LocalDate.now(), applyDate)) {
            throw new ApplyDateException("현재 날짜의 다음날부터 14일까지만 예약할 수 있습니다.", ErrorCode.APPLY_DATE_ERROR);
        }

        ApplyPlayClass applyPlayClass = ApplyPlayClass.builder()
                .parent(parent)
                .storePlayClass(storePlayClass)
                .applyDate(applyDate)
                .applyChildCnt(applyStorePlayClassRequestDto.getApplyChildCnt())
                .status(ApplyStatus.APPLY)
                .build();

        applyPlayClassRepository.apply(applyPlayClass);

        return ApplyPlayClassMapper.INSTANCE.entityToResponseDto(applyPlayClass);
    }

    public ApplyPlayClassDto.ApplyStorePlayClassResponseDto cancelStorePlayClass(ApplyPlayClassDto.CancelStorePlayClassRequestDto cancelStorePlayClassRequestDto) {
        Parent parent = parentRepository.findOne(cancelStorePlayClassRequestDto.getParentId());
        if (parent == null) {
            throw new NotFoundParentException("해당 부모님을 찾을 수 없습니다.", ErrorCode.NOT_FOUND_PARENT);
        }

        ApplyPlayClass applyPlayClass = applyPlayClassRepository.findByApplyPlayClassIdAndParentId(
                cancelStorePlayClassRequestDto.getApplyPlayClassId(), cancelStorePlayClassRequestDto.getParentId());
        if (applyPlayClass == null) {
            throw new NotFoundApplyPlayClassException("신청한 수업을 찾을 수 없습니다.", ErrorCode.NOT_FOUND_APPLY_CLASS);
        }

        applyPlayClass.changeApplyStatus(ApplyStatus.CANCEL);

        return ApplyPlayClassMapper.INSTANCE.entityToResponseDto(applyPlayClass);
    }

    public List<ApplyPlayClassDto.StorePlayClassApplyParentListResponseDto> findStorePlayClassApplyParentList(Long storePlayClassId) {
        StorePlayClass storePlayClass = storePlayClassRepository.findOne(storePlayClassId);
        if (storePlayClass == null) {
            throw new NotFoundStorePlayClassException("해당 매장의 수업을 찾을 수 없습니다.", ErrorCode.NOT_FOUND_STORE_PLAY_CLASS);
        }

        return ApplyPlayClassMapper.INSTANCE.entityListToListResponseDto(applyPlayClassRepository.findStorePlayClassApplyParentList(storePlayClassId));
    }

    public List<ApplyPlayClassDto.StorePlayClassApplyParentListResponseDto> findStorePlayClassAllParentList(Long storePlayClassId) {
        StorePlayClass storePlayClass = storePlayClassRepository.findOne(storePlayClassId);
        if (storePlayClass == null) {
            throw new NotFoundStorePlayClassException("해당 매장의 수업을 찾을 수 없습니다.", ErrorCode.NOT_FOUND_STORE_PLAY_CLASS);
        }

        return ApplyPlayClassMapper.INSTANCE.entityListToListResponseDto(applyPlayClassRepository.findStorePlayClassAllParentList(storePlayClassId));
    }

    public boolean isWithinRange(LocalDate currentDate, LocalDate dateToCheck) {
        LocalDate startDate = currentDate.plusDays(1);
        LocalDate endDate = currentDate.plusDays(14);

        return !dateToCheck.isBefore(startDate) && !dateToCheck.isAfter(endDate);
    }
}
