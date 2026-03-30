package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.inventory.InventoryDTO;
import com.iem.FilmRentalStore.dto.inventory.InventoryRequestDTO;
import com.iem.FilmRentalStore.dto.inventory.InventoryResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InventoryService {

    InventoryDTO createInventory(InventoryRequestDTO request);

    InventoryDTO getInventoryById(Integer id);

    InventoryResponseDTO getInventoryDetails(Integer id);

    Page<InventoryDTO> getAllInventory(Short filmId, Short storeId, Pageable pageable);

    InventoryDTO updateInventory(Integer id, InventoryRequestDTO request);

    Page<InventoryDTO> getByFilmId(Short filmId, Pageable pageable);

    Page<InventoryDTO> getByStoreId(Short storeId, Pageable pageable);

    InventoryDTO patchInventory(Integer id, InventoryRequestDTO request);
}