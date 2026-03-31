package com.iem.FilmRentalStore.dto.city;

import com.iem.FilmRentalStore.dto.country.CountryRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityRequestDTO {

    @NotBlank(message = "City name cannot be blank")
    @Size(max = 50, message = "City name must be at most 50 characters")
    private String city;

    @Valid
    private CountryRequestDTO country;
}
