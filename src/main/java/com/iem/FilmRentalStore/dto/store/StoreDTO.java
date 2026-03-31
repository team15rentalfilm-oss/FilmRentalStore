package com.iem.FilmRentalStore.dto.store;

import com.iem.FilmRentalStore.dto.address.AddressDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreDTO {

    private Integer storeId;
    private String managerName;
    private AddressDTO address;
}
