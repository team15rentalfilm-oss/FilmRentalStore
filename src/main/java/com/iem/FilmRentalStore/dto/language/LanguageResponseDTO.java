package com.iem.FilmRentalStore.dto.language;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LanguageResponseDTO {

    private Integer languageId;
    private String name;
    private LocalDateTime lastUpdate;
}