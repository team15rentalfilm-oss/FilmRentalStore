package com.iem.FilmRentalStore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Short customerId;

    @NotNull(message = "Store ID is mandatory")
    @Positive(message = "Store ID must be greater than 0")
    private Byte storeId;

    @NotBlank(message = "First name is mandatory")
    @Size(max = 45, message = "First name cannot exceed 45 characters")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(max = 45, message = "Last name cannot exceed 45 characters")
    private String lastName;

    @Email(message = "Email should be valid")
    @Size(max = 50, message = "Email cannot exceed 50 characters")
    private String email;

    @NotNull(message = "Address ID is mandatory")
    @Positive(message = "Address ID must be greater than 0")
    private Short addressId;

    @NotNull(message = "Active status is mandatory")
    private Boolean active;

    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;
}
