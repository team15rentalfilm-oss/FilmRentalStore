package com.iem.FilmRentalStore.dto.city;

import com.iem.FilmRentalStore.dto.country.CountryDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDTO {

    private String city;
    private CountryDTO country;
}
