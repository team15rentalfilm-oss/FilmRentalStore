package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.inventory.InventoryDTO;
import com.iem.FilmRentalStore.dto.inventory.InventoryRequestDTO;

import java.util.List;

public interface InventoryService {

    InventoryDTO createInventory(InventoryRequestDTO request);

    InventoryDTO getInventoryById(Integer id);

    List<InventoryDTO> getAllInventory();

    InventoryDTO updateInventory(Integer id, InventoryRequestDTO request);
}