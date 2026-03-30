package com.iem.FilmRentalStore.dto.rental;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RentalPatchDTO {

    private LocalDateTime returnDate;
    private Short staffId;
}