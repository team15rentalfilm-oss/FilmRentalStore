package com.iem.FilmRentalStore.dto.film;

import com.iem.FilmRentalStore.dto.actor.ActorDTO;
import com.iem.FilmRentalStore.dto.category.CategoryDTO;
import com.iem.FilmRentalStore.dto.language.LanguageDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import java.math.BigDecimal;

import java.util.Set;

@Getter
@Setter
public class FilmRequestDTO {

    @NotBlank(message = "Title is required")
    @Size(max = 255)
    private String title;

    private String description;

    private Integer releaseYear;

    @NotNull(message = "Language is required")
    @Valid
    private LanguageDTO language;

    @Min(1)
    private Integer rentalDuration;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal rentalRate;

    private Integer length;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal replacementCost;

    private String rating;

    private Set<String> specialFeatures;

    @Valid
    private List<CategoryDTO> categories;

    @Valid
    private List<ActorDTO> actors;
}