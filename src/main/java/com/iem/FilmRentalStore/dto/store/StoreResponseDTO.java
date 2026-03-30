package com.iem.FilmRentalStore.dto.store;

import com.iem.FilmRentalStore.dto.address.AddressDTO;
import com.iem.FilmRentalStore.dto.staff.StaffDTO;
import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
public class StoreResponseDTO {

    private Integer storeId;
    private StaffDTO manager;
    private AddressDTO address;
}