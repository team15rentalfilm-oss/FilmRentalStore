package com.iem.FilmRentalStore.dto.film;

import com.iem.FilmRentalStore.dto.actor.ActorDTO;
import com.iem.FilmRentalStore.dto.category.CategoryDTO;
import com.iem.FilmRentalStore.dto.language.LanguageDTO;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class FilmResponseDTO {

    private Short filmId;
    private String title;
    private String description;
    private Integer releaseYear;

    private LanguageDTO language;

    private Integer rentalDuration;
    private BigDecimal rentalRate;
    private Integer length;
    private BigDecimal replacementCost;

    private String rating;
    private Set<String> specialFeatures;

    private List<CategoryDTO> categories;
    private List<ActorDTO> actors;
    private LocalDateTime lastUpdate;
}