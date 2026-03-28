package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.language.LanguageDTO;
import com.iem.FilmRentalStore.dto.language.LanguageRequestDTO;
import com.iem.FilmRentalStore.dto.language.LanguageResponseDTO;

import java.util.List;

public interface LanguageService {

    LanguageResponseDTO createLanguage(LanguageRequestDTO request);

    LanguageResponseDTO getLanguageById(Integer id);

    List<LanguageResponseDTO> getAllLanguages();

    LanguageResponseDTO updateLanguage(Integer id, LanguageRequestDTO request);

    LanguageResponseDTO getLanguageByName(String name);
}