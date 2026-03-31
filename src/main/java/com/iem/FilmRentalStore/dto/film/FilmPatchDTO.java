package com.iem.FilmRentalStore.dto.film;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class FilmPatchDTO {

    private String title;
    private String description;
    private Integer releaseYear;
    private Integer rentalDuration;
    private BigDecimal rentalRate;
    private Integer length;
    private BigDecimal replacementCost;
    private String rating;
    private Set<String> specialFeatures;

    private String language;
    private Set<String> categories;
    private Set<String> actors;
}
