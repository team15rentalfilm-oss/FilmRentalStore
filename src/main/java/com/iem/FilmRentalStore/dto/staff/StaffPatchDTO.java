package com.iem.FilmRentalStore.dto.staff;

import com.iem.FilmRentalStore.dto.address.AddressRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffPatchDTO {

    @Size(min = 2, max = 45, message = "First name must be 2-45 characters")
    private String firstName;

    @Size(min = 2, max = 45, message = "Last name must be 2-45 characters")
    private String lastName;

    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 3, max = 16, message = "Username must be 3-16 characters")
    private String username;

    private Boolean active;

    @Size(min = 4, message = "Password must be at least 4 characters")
    private String password;

    private Short storeId;

    @Valid
    private AddressRequestDTO address;

    private Boolean isManager;
}
