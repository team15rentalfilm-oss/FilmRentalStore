package com.iem.FilmRentalStore.dto.actor;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class ActorResponseDTO {

    private Integer actorId;
    private String firstName;
    private String lastName;
}