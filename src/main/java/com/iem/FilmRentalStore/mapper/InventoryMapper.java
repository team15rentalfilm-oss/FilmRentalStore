package com.iem.FilmRentalStore.mapper;

import com.iem.FilmRentalStore.dto.inventory.*;
import com.iem.FilmRentalStore.entity.Film;
import com.iem.FilmRentalStore.entity.Inventory;
import com.iem.FilmRentalStore.entity.Store;

public class InventoryMapper {

    // 🔹 Request → Entity
    public static Inventory toEntity(Film film, Store store) {
        Inventory inventory = new Inventory();
        inventory.setFilm(film);
        inventory.setStore(store);
        return inventory;
    }

    // 🔹 Entity → Lightweight DTO
    public static InventoryDTO toDTO(Inventory inventory) {
        InventoryDTO dto = new InventoryDTO();
        dto.setFilmTitle(inventory.getFilm().getTitle());
        dto.setStoreName("Store " + inventory.getStore().getStoreId());
        return dto;
    }

    // 🔹 Entity → Response DTO (Detailed)
    public static InventoryResponseDTO toResponseDTO(Inventory inventory) {
        InventoryResponseDTO dto = new InventoryResponseDTO();

        dto.setInventoryId(inventory.getInventoryId());
        dto.setFilm(FilmMapper.toDTO(inventory.getFilm()));
        dto.setStore(StoreMapper.toDTO(inventory.getStore()));

        return dto;
    }
}