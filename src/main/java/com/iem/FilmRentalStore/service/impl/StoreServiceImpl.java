package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.StoreDTO;
import com.iem.FilmRentalStore.entity.Store;
import com.iem.FilmRentalStore.repository.StoreRepository;
import com.iem.FilmRentalStore.service.StoreService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public List<StoreDTO> getAllStores() {
        return storeRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public StoreDTO getStoreById(Byte id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with id: " + id));
        return toDTO(store);
    }

    @Override
    public StoreDTO createStore(StoreDTO storeDTO) {
        Store store = new Store();
        mapDtoToEntity(storeDTO, store);
        return toDTO(storeRepository.save(store));
    }

    @Override
    public StoreDTO updateStore(Byte id, StoreDTO storeDTO) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with id: " + id));

        if (storeDTO.getManagerStaffId() != null) {
            store.setManagerStaffId(storeDTO.getManagerStaffId());
        }
        if (storeDTO.getAddressId() != null) {
            store.setAddressId(storeDTO.getAddressId());
        }

        return toDTO(storeRepository.save(store));
    }

    @Override
    public void deleteStore(Byte id) {
        if (!storeRepository.existsById(id)) {
            throw new EntityNotFoundException("Store not found with id: " + id);
        }
        storeRepository.deleteById(id);
    }

    private void mapDtoToEntity(StoreDTO dto, Store store) {
        if (dto.getManagerStaffId() != null) {
            store.setManagerStaffId(dto.getManagerStaffId());
        }
        if (dto.getAddressId() != null) {
            store.setAddressId(dto.getAddressId());
        }
    }

    private StoreDTO toDTO(Store store) {
        return new StoreDTO(
                store.getStoreId(),
                store.getManagerStaffId(),
                store.getAddressId(),
                store.getLastUpdate()
        );
    }
}