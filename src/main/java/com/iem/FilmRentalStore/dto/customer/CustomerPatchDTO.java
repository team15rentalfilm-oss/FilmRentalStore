package com.iem.FilmRentalStore.dto.customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerPatchDTO {

    private String firstName;
    private String lastName;
    private String email;
    private Boolean active;

    private Short storeId;

    private String address;
    private String address2;
    private String district;
    private String postalCode;
    private String phone;

    private String city;
    private String country;
}
