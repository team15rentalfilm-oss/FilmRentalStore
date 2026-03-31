package com.iem.FilmRentalStore.dto.actor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ActorRequestDTO {

    @NotBlank(message = "First name is required")
    @Size(max = 45)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 45)
    private String lastName;
}
