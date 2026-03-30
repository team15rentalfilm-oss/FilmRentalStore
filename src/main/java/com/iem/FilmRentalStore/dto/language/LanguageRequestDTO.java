package com.iem.FilmRentalStore.dto.language;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LanguageRequestDTO {

    @NotBlank(message = "Language name is required")
    @Size(max = 20, message = "Language name must be at most 20 characters")
    private String name;
}