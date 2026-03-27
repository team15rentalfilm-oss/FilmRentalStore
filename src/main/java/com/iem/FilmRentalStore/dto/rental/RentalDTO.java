package com.iem.FilmRentalStore.dto.rental;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalDTO {

    private String filmTitle;
    private String storeName;
    private String rentalDate;
    private String returnDate;
}