package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.entity.FilmText;

import java.util.List;
import java.util.Map;

public interface FilmTextService {
    List<FilmText> getAllFilmTexts();
    List<FilmText> getFilmTextsByFields(Map<String, String> searchParams);
    FilmText getFilmTextById(Integer id);
    FilmText createFilmText(FilmText filmText);
    FilmText updateFilmText(Integer id, FilmText filmTextDetails);
    FilmText patchFilmText(Integer id, Map<String, Object> updates);
    void deleteFilmText(Integer id);
}
