package com.tictoccroc.island.mapper;

import com.tictoccroc.island.dto.StorePlayClassDto;
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
    date = "2024-03-08T23:23:29+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class StorePlayClassMapperImpl implements StorePlayClassMapper {

    @Override
    public StorePlayClassDto.ParentApplyInfoDto entityToDto(ApplyPlayClass applyPlayClass) {
        if ( applyPlayClass == null ) {
            return null;
        }

        StorePlayClassDto.ParentApplyInfoDto parentApplyInfoDto = new StorePlayClassDto.ParentApplyInfoDto();

        parentApplyInfoDto.setParentName( applyPlayClassParentName( applyPlayClass ) );
        parentApplyInfoDto.setStatus( applyPlayClass.getStatus() );
        parentApplyInfoDto.setApplyChildCnt( applyPlayClass.getApplyChildCnt() );
        parentApplyInfoDto.setApplyDate( applyPlayClass.getApplyDate() );

        return parentApplyInfoDto;
    }

    @Override
    public List<StorePlayClassDto.ParentApplyInfoDto> entityToList(List<ApplyPlayClass> applyPlayClassList) {
        if ( applyPlayClassList == null ) {
            return null;
        }

        List<StorePlayClassDto.ParentApplyInfoDto> list = new ArrayList<StorePlayClassDto.ParentApplyInfoDto>( applyPlayClassList.size() );
        for ( ApplyPlayClass applyPlayClass : applyPlayClassList ) {
            list.add( entityToDto( applyPlayClass ) );
        }

        return list;
    }

    @Override
    public StorePlayClassDto.HistoryStorePlayClassResponseDto entityToHistoryDto(StorePlayClass storePlayClass) {
        if ( storePlayClass == null ) {
            return null;
        }

        StorePlayClassDto.HistoryStorePlayClassResponseDto historyStorePlayClassResponseDto = new StorePlayClassDto.HistoryStorePlayClassResponseDto();

        historyStorePlayClassResponseDto.setStoreClassId( storePlayClass.getId() );
        historyStorePlayClassResponseDto.setStoreName( storePlayClassStoreName( storePlayClass ) );
        historyStorePlayClassResponseDto.setPlayClassName( storePlayClassPlayClassName( storePlayClass ) );
        historyStorePlayClassResponseDto.setParentApplyInfoDtoList( entityToList( storePlayClass.getApplyPlayClassList() ) );

        return historyStorePlayClassResponseDto;
    }

    @Override
    public List<StorePlayClassDto.HistoryStorePlayClassResponseDto> entityListToHistoryListDto(List<StorePlayClass> applyPlayClassList) {
        if ( applyPlayClassList == null ) {
            return null;
        }

        List<StorePlayClassDto.HistoryStorePlayClassResponseDto> list = new ArrayList<StorePlayClassDto.HistoryStorePlayClassResponseDto>( applyPlayClassList.size() );
        for ( StorePlayClass storePlayClass : applyPlayClassList ) {
            list.add( entityToHistoryDto( storePlayClass ) );
        }

        return list;
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

    private String storePlayClassStoreName(StorePlayClass storePlayClass) {
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

    private String storePlayClassPlayClassName(StorePlayClass storePlayClass) {
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
}
