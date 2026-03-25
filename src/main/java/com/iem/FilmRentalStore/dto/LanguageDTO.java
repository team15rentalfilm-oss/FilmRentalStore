package com.iem.FilmRentalStore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LanguageDTO {

    private int languageId;

    @NotBlank(message = "Language name is mandatory")
    @Size(max = 20, message = "Language name cannot exceed 20 characters")
    private String name;
}
