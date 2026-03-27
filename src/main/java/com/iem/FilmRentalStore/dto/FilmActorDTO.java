package com.iem.FilmRentalStore.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmActorDTO {

    @NotNull(message = "Actor ID is mandatory")
    @Positive(message = "Actor ID must be greater than 0")
    private Short actorId;

    @NotNull(message = "Film ID is mandatory")
    @Positive(message = "Film ID must be greater than 0")
    private Short filmId;
}