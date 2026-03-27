package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.FilmTextDTO;

import java.util.List;
import java.util.Map;

public interface FilmTextService {
    List<FilmTextDTO> getAllFilmTexts();
    List<FilmTextDTO> getFilmTextsByFields(Map<String, String> searchParams);
    FilmTextDTO getFilmTextById(Short id);
    FilmTextDTO createFilmText(FilmTextDTO filmTextDto);
    FilmTextDTO updateFilmText(Short id, FilmTextDTO filmTextDto);
    FilmTextDTO patchFilmText(Short id, Map<String, Object> updates);
    void deleteFilmText(Short id);
}