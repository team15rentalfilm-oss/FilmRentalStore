package com.iem.FilmRentalStore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmDTO {

    private Short id;

    @NotBlank(message = "Title is mandatory")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    private String title;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    private Integer releaseYear;

    @NotNull(message = "Language ID is mandatory")
    private Byte languageId;

    private Byte originalLanguageId;

    @NotNull(message = "Rental duration is mandatory")
    private Short rentalDuration;

    @NotNull(message = "Rental rate is mandatory")
    private BigDecimal rentalRate;

    private Short length;

    @NotNull(message = "Replacement cost is mandatory")
    private BigDecimal replacementCost;

    private String rating;

    private String specialFeatures;

    private Set<Byte> categoryIds;
}