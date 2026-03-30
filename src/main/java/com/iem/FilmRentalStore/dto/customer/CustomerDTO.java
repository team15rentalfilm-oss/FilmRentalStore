package com.iem.FilmRentalStore.dto.customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    private Short id;
    private String firstName;
    private String lastName;
    private String email;
}