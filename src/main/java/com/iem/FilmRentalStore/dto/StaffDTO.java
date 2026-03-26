package com.iem.FilmRentalStore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StaffDTO {
    private Integer staffId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    private Integer addressId;

    @Email
    private String email;

    @NotNull
    private Integer storeId;

    @NotNull
    private Boolean active;

    @NotBlank
    private String username;
}