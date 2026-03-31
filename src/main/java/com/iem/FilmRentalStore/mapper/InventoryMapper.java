package com.iem.FilmRentalStore.mapper;

import com.iem.FilmRentalStore.dto.inventory.*;
import com.iem.FilmRentalStore.entity.Film;
import com.iem.FilmRentalStore.entity.Inventory;
import com.iem.FilmRentalStore.entity.Store;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public static Inventory toEntity(Film film, Store store) {
        Inventory inventory = new Inventory();
        inventory.setFilm(film);
        inventory.setStore(store);
        return inventory;
    }

    public static InventoryDTO toDTO(Inventory inventory) {
        InventoryDTO dto = new InventoryDTO();
        dto.setInventoryId(inventory.getInventoryId());
        dto.setFilmTitle(inventory.getFilm().getTitle());
        dto.setStoreId(inventory.getStore().getStoreId());
        return dto;
    }

    public static InventoryResponseDTO toResponseDTO(Inventory inventory) {
        InventoryResponseDTO dto = new InventoryResponseDTO();

        dto.setInventoryId(inventory.getInventoryId());
        dto.setFilm(FilmMapper.toDTO(inventory.getFilm()));
        dto.setStore(StoreMapper.toDTO(inventory.getStore()));

        return dto;
    }
}
