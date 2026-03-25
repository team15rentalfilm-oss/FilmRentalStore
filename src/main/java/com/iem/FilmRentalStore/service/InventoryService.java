package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.entity.Inventory;

import java.util.List;
import java.util.Map;

public interface InventoryService {
    List<Inventory> getAllInventories();
    List<Inventory> getInventoriesByFields(Map<String, String> searchParams);
    Inventory getInventoryById(Integer id);
    Inventory createInventory(Inventory inventory);
    Inventory updateInventory(Integer id, Inventory inventoryDetails);
    Inventory patchInventory(Integer id, Map<String, Object> updates);
    void deleteInventory(Integer id);
}
