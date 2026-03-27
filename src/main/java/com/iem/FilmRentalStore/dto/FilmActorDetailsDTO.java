package com.iem.FilmRentalStore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmActorDetailsDTO {

    private Integer actorId;
    private String actorFirstName;
    private String actorLastName;
    private Integer filmId;
    private String filmTitle;
}
