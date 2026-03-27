package com.iem.FilmRentalStore.dto.staff;

import com.iem.FilmRentalStore.dto.address.AddressRequestDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class StaffRequestDTO {

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

    @NotNull(message = "Store ID is required")
    private Integer storeId;

    @NotBlank(message = "Username is required")
    @Size(max = 16)
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    private Boolean active = true;
}