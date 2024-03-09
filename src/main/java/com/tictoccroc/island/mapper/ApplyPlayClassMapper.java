package com.tictoccroc.island.mapper;

import com.tictoccroc.island.dto.ApplyPlayClassDto;
import com.tictoccroc.island.model.ApplyPlayClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ApplyPlayClassMapper {

    ApplyPlayClassMapper INSTANCE = Mappers.getMapper(ApplyPlayClassMapper.class);

    @Mapping(source = "id", target = "applyPlayClassId")
    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "storePlayClass.id", target = "storePlayClassId")
    ApplyPlayClassDto.ApplyStorePlayClassResponseDto entityToResponseDto(ApplyPlayClass applyPlayClass);

    @Mapping(source = "id", target = "applyPlayClassId")
    @Mapping(source = "storePlayClass.store.name", target = "storeName")
    @Mapping(source = "storePlayClass.playClass.name", target = "playClassName")
    @Mapping(source = "parent.name", target = "parentName")
    ApplyPlayClassDto.StorePlayClassApplyParentListResponseDto entityToListResponseDto(ApplyPlayClass applyPlayClass);

    List<ApplyPlayClassDto.StorePlayClassApplyParentListResponseDto> entityListToListResponseDto(List<ApplyPlayClass> applyPlayClassList);
}
