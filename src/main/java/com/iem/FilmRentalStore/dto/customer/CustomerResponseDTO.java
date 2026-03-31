package com.iem.FilmRentalStore.dto.customer;

import com.iem.FilmRentalStore.dto.address.AddressDTO;
import com.iem.FilmRentalStore.dto.store.StoreDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class CustomerResponseDTO {

    private Short customerId;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean active;

    private AddressDTO address;
    private StoreDTO store;

    private LocalDateTime createDate;
}
