package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.store.StoreDTO;
import com.iem.FilmRentalStore.dto.store.StoreRequestDTO;

import java.util.List;

public interface StoreService {

    StoreDTO createStore(StoreRequestDTO request);

    StoreDTO getStoreById(Short id);

    List<StoreDTO> getAllStores();

    StoreDTO updateStore(Short id, StoreRequestDTO request);
}