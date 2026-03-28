package com.iem.FilmRentalStore.dto.film;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class FilmPatchDTO {

    private String title;
    private String description;
    private Integer releaseYear;
    private String rating;

    private String language;
    private Set<String> categories;
    private Set<String> actors;
}