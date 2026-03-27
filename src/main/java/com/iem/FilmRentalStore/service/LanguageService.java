package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.language.LanguageDTO;
import com.iem.FilmRentalStore.dto.language.LanguageRequestDTO;

import java.util.List;

public interface LanguageService {

    LanguageDTO createLanguage(LanguageRequestDTO request);

    LanguageDTO getLanguageById(Integer id);

    List<LanguageDTO> getAllLanguages();

    LanguageDTO updateLanguage(Integer id, LanguageRequestDTO request);

}