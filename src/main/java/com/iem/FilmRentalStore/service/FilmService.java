package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.film.FilmRequestDTO;
import com.iem.FilmRentalStore.dto.film.FilmResponseDTO;

import java.util.List;

public interface FilmService {

    FilmResponseDTO createFilm(FilmRequestDTO request);

    FilmResponseDTO getFilmById(Short id);

    List<FilmResponseDTO> getAllFilms();

    FilmResponseDTO updateFilm(Short id, FilmRequestDTO request);

    FilmResponseDTO patchFilm(Short id, FilmRequestDTO request);

    void deleteFilm(Short id);

    List<FilmResponseDTO> searchFilms(String title, Integer year);
}