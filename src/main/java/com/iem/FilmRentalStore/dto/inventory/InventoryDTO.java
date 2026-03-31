package com.iem.FilmRentalStore.dto.inventory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryDTO {

    private Integer inventoryId;
    private String filmTitle;
    private Integer storeId;
}
