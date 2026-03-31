package com.iem.FilmRentalStore.dto.country;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryRequestDTO {

    @NotBlank(message = "Country name cannot be blank")
    @Size(max = 50, message = "Country name must be at most 50 characters")
    private String country;
}
