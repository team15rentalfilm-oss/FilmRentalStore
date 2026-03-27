package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.StoreDTO;

import java.util.List;

public interface StoreService {
    List<StoreDTO> getAllStores();
    StoreDTO getStoreById(Byte id);
    StoreDTO createStore(StoreDTO storeDTO);
    StoreDTO updateStore(Byte id, StoreDTO storeDTO);
    void deleteStore(Byte id);
}