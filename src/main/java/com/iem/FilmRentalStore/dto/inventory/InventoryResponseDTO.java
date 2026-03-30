package com.iem.FilmRentalStore.dto.inventory;



import com.iem.FilmRentalStore.dto.film.FilmDTO;
import com.iem.FilmRentalStore.dto.store.StoreDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryResponseDTO {

    private Integer inventoryId;
    private FilmDTO film;
    private StoreDTO store;
}