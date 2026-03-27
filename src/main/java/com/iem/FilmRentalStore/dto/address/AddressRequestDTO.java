package com.iem.FilmRentalStore.dto.address;

import com.iem.FilmRentalStore.dto.city.CityDTO;
import com.iem.FilmRentalStore.dto.city.CityRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequestDTO {

    @NotBlank(message = "Address cannot be blank")
    @Size(max = 50)
    private String address;

    @Size(max = 50)
    private String address2;

    @NotBlank(message = "District is required")
    @Size(max = 20)
    private String district;

    @NotBlank(message = "Postal code is required")
    @Size(max = 10)
    private String postalCode;

    @NotBlank(message = "Phone is required")
    @Size(max = 20)
    private String phone;

    @Valid
    private CityRequestDTO city;
}