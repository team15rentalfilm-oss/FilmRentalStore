package com.iem.FilmRentalStore.dto.address;

import com.iem.FilmRentalStore.dto.city.CityDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponseDTO {

    private Short addressId;
    private String address;
    private String address2;
    private String district;
    private String postalCode;
    private String phone;
    private CityDTO city;
}
