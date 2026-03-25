package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.FilmDTO;

import java.util.List;

public interface FilmService {

    FilmDTO createFilm(FilmDTO filmDTO);

    FilmDTO getFilmById(Short id);

    List<FilmDTO> getAllFilms();

    FilmDTO updateFilm(Short id, FilmDTO filmDTO);

    void deleteFilm(Short id);



    List<FilmDTO> searchFilms(String title, Integer year);
    FilmDTO patchFilm(Short id, FilmDTO filmDTO);

}
