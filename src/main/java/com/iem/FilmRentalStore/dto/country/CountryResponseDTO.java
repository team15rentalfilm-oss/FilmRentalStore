package com.iem.FilmRentalStore.dto.country;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CountryResponseDTO {

    private Short countryId;
    private String country;
    private LocalDateTime lastUpdate;
}