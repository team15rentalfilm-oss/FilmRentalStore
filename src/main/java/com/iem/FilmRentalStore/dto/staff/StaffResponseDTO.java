package com.iem.FilmRentalStore.dto.staff;

import com.iem.FilmRentalStore.dto.address.AddressDTO;
import com.iem.FilmRentalStore.dto.store.StoreDTO;
import lombok.Setter;

import lombok.Getter;


@Getter
@Setter
public class StaffResponseDTO {

    private Integer staffId;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean active;

    private AddressDTO address;
    private StoreDTO store;
}
