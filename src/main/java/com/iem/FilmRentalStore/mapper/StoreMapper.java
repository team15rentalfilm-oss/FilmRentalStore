package com.iem.FilmRentalStore.mapper;


import com.iem.FilmRentalStore.dto.store.*;
import com.iem.FilmRentalStore.entity.Address;
import com.iem.FilmRentalStore.entity.Staff;
import com.iem.FilmRentalStore.entity.Store;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {

    // RequestDTO → Entity
    public static Store toEntity(StoreRequestDTO dto, Address address, Staff manager) {
        Store store = new Store();

        store.setAddress(address);
        store.setManagerStaff(manager);

        return store;
    }

    // Entity → ResponseDTO
    public static StoreResponseDTO toResponseDTO(Store store) {
        StoreResponseDTO dto = new StoreResponseDTO();

        dto.setStoreId(store.getStoreId());
        dto.setAddress(AddressMapper.toDTO(store.getAddress()));
        dto.setManager(StaffMapper.toDTO(store.getManagerStaff()));

        return dto;
    }

    // Entity → Lightweight DTO
    public static StoreDTO toDTO(Store store) {
        StoreDTO dto = new StoreDTO();

        dto.setStoreId(store.getStoreId());

        String managerName = store.getManagerStaff().getFirstName() + " " +
                store.getManagerStaff().getLastName();

        dto.setManagerName(managerName);

        return dto;
    }
}