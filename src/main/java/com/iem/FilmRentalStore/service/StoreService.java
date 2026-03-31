package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.store.StoreDTO;
import com.iem.FilmRentalStore.dto.store.StoreRequestDTO;
import com.iem.FilmRentalStore.dto.store.StoreResponseDTO;

import java.util.List;

public interface StoreService {

    StoreResponseDTO createStore(StoreRequestDTO request);

    StoreResponseDTO getStoreById(Short id);

    List<StoreDTO> getAllStores();

    StoreResponseDTO updateStore(Short id, StoreRequestDTO request);
}