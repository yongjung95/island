package com.tictoccroc.island.service;

import com.tictoccroc.island.dto.ApplyPlayClassDto;
import com.tictoccroc.island.error.ApplyMaxCntException;
import com.tictoccroc.island.error.NotFoundStorePlayClassException;
import com.tictoccroc.island.model.*;
import com.tictoccroc.island.repository.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplyPlayClassServiceTest {

    @Autowired
    ApplyPlayClassService applyPlayClassService;

    @Autowired
    ParentRepository parentRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    PlayClassRepository playClassRepository;

    @Autowired
    StorePlayClassRepository storePlayClassRepository;

    @Autowired
    ApplyPlayClassRepository applyPlayClassRepository;

    @Autowired
    EntityManager em;

    @BeforeEach
    void 초기화(){
        Parent parent = Parent.createParent(1L, "아빠", "father@gmail.com");

        parentRepository.save(parent);

        Store store = Store.createStore(1L, "잠실점", "서울시 잠실");
        Store store2 = Store.createStore(2L, "일산점", "경기도 일산");
        Store store3 = Store.createStore(3L, "판교점", "경기도 판교");

        storeRepository.save(store);
        storeRepository.save(store2);
        storeRepository.save(store3);

        PlayClass playClass = PlayClass.createPlayClass(1L, "도시농부", 20);
        PlayClass playClass2 = PlayClass.createPlayClass(2L, "드로잉", 10);
        PlayClass playClass3 = PlayClass.createPlayClass(3L, "오감 놀이", 2);

        playClassRepository.save(playClass);
        playClassRepository.save(playClass2);
        playClassRepository.save(playClass3);

        StorePlayClass storePlayClass = StorePlayClass.createStorePlayClass(1L, store, playClass);
        StorePlayClass storePlayClass1 = StorePlayClass.createStorePlayClass(2L, store, playClass2);
        StorePlayClass storePlayClass2 = StorePlayClass.createStorePlayClass(3L, store, playClass3);

        storePlayClassRepository.save(storePlayClass);
        storePlayClassRepository.save(storePlayClass1);
        storePlayClassRepository.save(storePlayClass2);

    }

    @Test
    @Order(value = 1)
    void 예약_성공() {
        Parent parent = parentRepository.findOne(1L);
        StorePlayClass storePlayClass = storePlayClassRepository.findOne(1L);

        ApplyPlayClassDto.ApplyStorePlayClassRequestDto applyStorePlayClassRequestDto = new ApplyPlayClassDto.ApplyStorePlayClassRequestDto();
        applyStorePlayClassRequestDto.setParentId(parent.getId());
        applyStorePlayClassRequestDto.setStorePlayClassId(storePlayClass.getId());
        applyStorePlayClassRequestDto.setApplyDate("2024-03-20");
        applyStorePlayClassRequestDto.setApplyChildCnt(3);

        ApplyPlayClassDto.ApplyStorePlayClassResponseDto resultDto = applyPlayClassService.applyStorePlayClass(applyStorePlayClassRequestDto);
        Assertions.assertEquals(resultDto.getParentId(), applyStorePlayClassRequestDto.getParentId());
        Assertions.assertEquals(resultDto.getStatus(), ApplyStatus.APPLY);
    }

    @Test
    @Order(value = 2)
    void 예약_실패_정원초과() {
        Parent parent = parentRepository.findOne(1L);
        StorePlayClass storePlayClass = storePlayClassRepository.findOne(3L);

        System.out.println("parent = " + parent);

        ApplyPlayClassDto.ApplyStorePlayClassRequestDto applyStorePlayClassRequestDto = new ApplyPlayClassDto.ApplyStorePlayClassRequestDto();
        applyStorePlayClassRequestDto.setParentId(parent.getId());
        applyStorePlayClassRequestDto.setStorePlayClassId(storePlayClass.getId());
        applyStorePlayClassRequestDto.setApplyDate("2024-03-20");
        applyStorePlayClassRequestDto.setApplyChildCnt(3);

        Assertions.assertThrows(ApplyMaxCntException.class, () -> {
            applyPlayClassService.applyStorePlayClass(applyStorePlayClassRequestDto);
        });
    }

    @Test
    @Order(value = 3)
    void 예약_실패_수업을_찾지_못함() {
        Parent parent = parentRepository.findOne(1L);

        ApplyPlayClassDto.ApplyStorePlayClassRequestDto applyStorePlayClassRequestDto = new ApplyPlayClassDto.ApplyStorePlayClassRequestDto();
        applyStorePlayClassRequestDto.setParentId(parent.getId());
        applyStorePlayClassRequestDto.setStorePlayClassId(4L);
        applyStorePlayClassRequestDto.setApplyDate("2024-03-20");
        applyStorePlayClassRequestDto.setApplyChildCnt(3);

        Assertions.assertThrows(NotFoundStorePlayClassException.class, () -> {
            applyPlayClassService.applyStorePlayClass(applyStorePlayClassRequestDto);
        });
    }

    @Test
    @Order(value = 4)
    void 예약_취소() {
        Parent parent = parentRepository.findOne(1L);
        StorePlayClass storePlayClass = storePlayClassRepository.findOne(1L);

        ApplyPlayClass applyPlayClass = ApplyPlayClass.builder()
                .parent(parent)
                .storePlayClass(storePlayClass)
                .applyDate(LocalDate.now())
                .applyChildCnt(3)
                .status(ApplyStatus.APPLY)
                .build();

        applyPlayClassRepository.apply(applyPlayClass);

        ApplyPlayClassDto.CancelStorePlayClassRequestDto cancelStorePlayClassRequestDto = new ApplyPlayClassDto.CancelStorePlayClassRequestDto();
        cancelStorePlayClassRequestDto.setParentId(parent.getId());
        cancelStorePlayClassRequestDto.setApplyPlayClassId(applyPlayClass.getId());

        applyPlayClassService.cancelStorePlayClass(cancelStorePlayClassRequestDto);

        ApplyPlayClass findApplyPlayClass = applyPlayClassRepository.findByApplyPlayClassIdAndParentId(applyPlayClass.getId(), parent.getId());

        Assertions.assertEquals(findApplyPlayClass.getStatus(), ApplyStatus.CANCEL);
    }

    @Test
    @Order(value = 5)
    void 예약자_현황_조회() {
        Parent parent = parentRepository.findOne(1L);
        StorePlayClass storePlayClass = storePlayClassRepository.findOne(1L);

        ApplyPlayClass applyPlayClass = ApplyPlayClass.builder()
                .parent(parent)
                .storePlayClass(storePlayClass)
                .applyDate(LocalDate.now())
                .applyChildCnt(3)
                .status(ApplyStatus.APPLY)
                .build();

        ApplyPlayClass applyPlayClass2 = ApplyPlayClass.builder()
                .parent(parent)
                .storePlayClass(storePlayClass)
                .applyDate(LocalDate.now())
                .applyChildCnt(3)
                .status(ApplyStatus.CANCEL)
                .build();

        applyPlayClassRepository.apply(applyPlayClass);
        applyPlayClassRepository.apply(applyPlayClass2);

        List<ApplyPlayClassDto.StorePlayClassApplyParentListResponseDto> resultList = applyPlayClassService.findStorePlayClassApplyParentList(storePlayClass.getId());

        Assertions.assertEquals(resultList.size(), 1);

        for (ApplyPlayClassDto.StorePlayClassApplyParentListResponseDto storePlayClassApplyParentListResponseDto : resultList) {
            System.out.println("storePlayClassApplyParentListResponseDto = " + storePlayClassApplyParentListResponseDto);
        }
    }


    @Test
    @Order(value = 6)
    void 예약_이력_현황_조회() {
        Parent parent = parentRepository.findOne(1L);
        StorePlayClass storePlayClass = storePlayClassRepository.findOne(1L);

        ApplyPlayClass applyPlayClass = ApplyPlayClass.builder()
                .parent(parent)
                .storePlayClass(storePlayClass)
                .applyDate(LocalDate.now())
                .applyChildCnt(3)
                .status(ApplyStatus.APPLY)
                .build();

        ApplyPlayClass applyPlayClass2 = ApplyPlayClass.builder()
                .parent(parent)
                .storePlayClass(storePlayClass)
                .applyDate(LocalDate.now())
                .applyChildCnt(3)
                .status(ApplyStatus.CANCEL)
                .build();

        applyPlayClassRepository.apply(applyPlayClass);
        applyPlayClassRepository.apply(applyPlayClass2);

        List<ApplyPlayClassDto.StorePlayClassApplyParentListResponseDto> resultList = applyPlayClassService.findStorePlayClassAllParentList(storePlayClass.getId());

        Assertions.assertEquals(resultList.size(), 2);

        for (ApplyPlayClassDto.StorePlayClassApplyParentListResponseDto storePlayClassApplyParentListResponseDto : resultList) {
            System.out.println("storePlayClassApplyParentListResponseDto = " + storePlayClassApplyParentListResponseDto);
        }
    }
}