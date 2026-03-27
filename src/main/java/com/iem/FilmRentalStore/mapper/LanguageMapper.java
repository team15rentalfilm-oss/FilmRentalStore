package com.iem.FilmRentalStore.mapper;


import com.iem.FilmRentalStore.dto.language.*;
import com.iem.FilmRentalStore.entity.Language;



public class LanguageMapper {

    //RequestDTO → Entity
    public static Language toEntity(LanguageRequestDTO dto) {
        Language language = new Language();
        language.setName(dto.getName());
        return language;
    }

    // Entity → ResponseDTO
    public static LanguageResponseDTO toResponseDTO(Language language) {
        LanguageResponseDTO dto = new LanguageResponseDTO();
        dto.setLanguageId(language.getLanguageId());
        dto.setName(language.getName());
        return dto;
    }

    // Entity → Lightweight DTO
    public static LanguageDTO toDTO(Language language) {
        LanguageDTO dto = new LanguageDTO();
        dto.setName(language.getName());
        return dto;
    }
}