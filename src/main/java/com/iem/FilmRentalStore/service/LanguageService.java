package com.iem.FilmRentalStore.service;

import java.util.List;

public interface LanguageService {

    List<LanguageDTO> getAll();

    LanguageDTO getById(int id);

    List<LanguageDTO> getByName(String name);

    LanguageDTO create(LanguageDTO dto);

    LanguageDTO update(int id, LanguageDTO dto);

    LanguageDTO patch(int id, LanguageDTO dto);

    void delete(int id);
}