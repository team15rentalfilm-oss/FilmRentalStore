package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.InventoryDTO;

import java.util.List;
import java.util.Map;

public interface InventoryService {

    List<InventoryDTO> getAllInventories();

    List<InventoryDTO> getInventoriesByFields(Map<String, String> searchParams);

    InventoryDTO getInventoryById(Integer id);

    InventoryDTO createInventory(InventoryDTO inventoryDTO);

    InventoryDTO updateInventory(Integer id, InventoryDTO inventoryDTO);

    InventoryDTO patchInventory(Integer id, Map<String, Object> updates);

    void deleteInventory(Integer id);
}