package com.iem.FilmRentalStore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActorDTO {

    //private int actorId;

    @NotBlank(message = "First name is mandatory")
    @Size(max = 45, message = "First name cannot exceed 45 characters")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(max = 45, message = "Last name cannot exceed 45 characters")
    private String lastName;
}

