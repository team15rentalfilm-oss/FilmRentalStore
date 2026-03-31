package com.iem.FilmRentalStore.mapper;


import com.iem.FilmRentalStore.dto.language.*;
import com.iem.FilmRentalStore.entity.Language;
import org.springframework.stereotype.Component;


@Component
public class LanguageMapper {

    public static Language toEntity(LanguageRequestDTO dto) {
        Language language = new Language();
        language.setName(dto.getName());
        return language;
    }

    public static LanguageResponseDTO toResponseDTO(Language language) {
        LanguageResponseDTO dto = new LanguageResponseDTO();
        dto.setLanguageId(language.getLanguageId());
        dto.setName(language.getName());
        dto.setLastUpdate(language.getLastUpdate());
        return dto;
    }

    public static LanguageDTO toDTO(Language language) {
        LanguageDTO dto = new LanguageDTO();
        dto.setName(language.getName());
        return dto;
    }
}
