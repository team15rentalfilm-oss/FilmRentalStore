package com.iem.FilmRentalStore.dto.customer;

import com.iem.FilmRentalStore.dto.address.AddressRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDTO {

    @NotBlank(message = "First name is required")
    @Size(max = 45)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 45)
    private String lastName;

    @Email(message = "Invalid email format")
    @Size(max = 50)
    private String email;

    @NotNull(message = "Address is required")
    @Valid
    private AddressRequestDTO address;

    // Since store is predefined, we use ID here (practical exception)
    @NotNull(message = "Store ID is required")
    private Short storeId;

    private Boolean active = true;
}