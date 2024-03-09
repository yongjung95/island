package com.tictoccroc.island.mapper;

import com.tictoccroc.island.dto.ApplyPlayClassDto;
import com.tictoccroc.island.model.ApplyPlayClass;
import com.tictoccroc.island.model.Parent;
import com.tictoccroc.island.model.PlayClass;
import com.tictoccroc.island.model.Store;
import com.tictoccroc.island.model.StorePlayClass;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-09T14:22:47+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class ApplyPlayClassMapperImpl implements ApplyPlayClassMapper {

    @Override
    public ApplyPlayClassDto.ApplyStorePlayClassResponseDto entityToResponseDto(ApplyPlayClass applyPlayClass) {
        if ( applyPlayClass == null ) {
            return null;
        }

        ApplyPlayClassDto.ApplyStorePlayClassResponseDto applyStorePlayClassResponseDto = new ApplyPlayClassDto.ApplyStorePlayClassResponseDto();

        applyStorePlayClassResponseDto.setApplyPlayClassId( applyPlayClass.getId() );
        applyStorePlayClassResponseDto.setParentId( applyPlayClassParentId( applyPlayClass ) );
        applyStorePlayClassResponseDto.setStorePlayClassId( applyPlayClassStorePlayClassId( applyPlayClass ) );
        applyStorePlayClassResponseDto.setStatus( applyPlayClass.getStatus() );
        applyStorePlayClassResponseDto.setApplyChildCnt( applyPlayClass.getApplyChildCnt() );
        applyStorePlayClassResponseDto.setApplyDate( applyPlayClass.getApplyDate() );

        return applyStorePlayClassResponseDto;
    }

    @Override
    public ApplyPlayClassDto.StorePlayClassApplyParentListResponseDto entityToListResponseDto(ApplyPlayClass applyPlayClass) {
        if ( applyPlayClass == null ) {
            return null;
        }

        ApplyPlayClassDto.StorePlayClassApplyParentListResponseDto storePlayClassApplyParentListResponseDto = new ApplyPlayClassDto.StorePlayClassApplyParentListResponseDto();

        storePlayClassApplyParentListResponseDto.setApplyPlayClassId( applyPlayClass.getId() );
        storePlayClassApplyParentListResponseDto.setStoreName( applyPlayClassStorePlayClassStoreName( applyPlayClass ) );
        storePlayClassApplyParentListResponseDto.setPlayClassName( applyPlayClassStorePlayClassPlayClassName( applyPlayClass ) );
        storePlayClassApplyParentListResponseDto.setParentName( applyPlayClassParentName( applyPlayClass ) );
        storePlayClassApplyParentListResponseDto.setApplyChildCnt( applyPlayClass.getApplyChildCnt() );
        storePlayClassApplyParentListResponseDto.setStatus( applyPlayClass.getStatus() );
        storePlayClassApplyParentListResponseDto.setApplyDate( applyPlayClass.getApplyDate() );

        return storePlayClassApplyParentListResponseDto;
    }

    @Override
    public List<ApplyPlayClassDto.StorePlayClassApplyParentListResponseDto> entityListToListResponseDto(List<ApplyPlayClass> applyPlayClassList) {
        if ( applyPlayClassList == null ) {
            return null;
        }

        List<ApplyPlayClassDto.StorePlayClassApplyParentListResponseDto> list = new ArrayList<ApplyPlayClassDto.StorePlayClassApplyParentListResponseDto>( applyPlayClassList.size() );
        for ( ApplyPlayClass applyPlayClass : applyPlayClassList ) {
            list.add( entityToListResponseDto( applyPlayClass ) );
        }

        return list;
    }

    private Long applyPlayClassParentId(ApplyPlayClass applyPlayClass) {
        if ( applyPlayClass == null ) {
            return null;
        }
        Parent parent = applyPlayClass.getParent();
        if ( parent == null ) {
            return null;
        }
        Long id = parent.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long applyPlayClassStorePlayClassId(ApplyPlayClass applyPlayClass) {
        if ( applyPlayClass == null ) {
            return null;
        }
        StorePlayClass storePlayClass = applyPlayClass.getStorePlayClass();
        if ( storePlayClass == null ) {
            return null;
        }
        Long id = storePlayClass.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String applyPlayClassStorePlayClassStoreName(ApplyPlayClass applyPlayClass) {
        if ( applyPlayClass == null ) {
            return null;
        }
        StorePlayClass storePlayClass = applyPlayClass.getStorePlayClass();
        if ( storePlayClass == null ) {
            return null;
        }
        Store store = storePlayClass.getStore();
        if ( store == null ) {
            return null;
        }
        String name = store.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String applyPlayClassStorePlayClassPlayClassName(ApplyPlayClass applyPlayClass) {
        if ( applyPlayClass == null ) {
            return null;
        }
        StorePlayClass storePlayClass = applyPlayClass.getStorePlayClass();
        if ( storePlayClass == null ) {
            return null;
        }
        PlayClass playClass = storePlayClass.getPlayClass();
        if ( playClass == null ) {
            return null;
        }
        String name = playClass.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String applyPlayClassParentName(ApplyPlayClass applyPlayClass) {
        if ( applyPlayClass == null ) {
            return null;
        }
        Parent parent = applyPlayClass.getParent();
        if ( parent == null ) {
            return null;
        }
        String name = parent.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
