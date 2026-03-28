package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.film.FilmPatchDTO;
import com.iem.FilmRentalStore.dto.film.FilmRequestDTO;
import com.iem.FilmRentalStore.dto.film.FilmResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FilmService {

    FilmResponseDTO createFilm(FilmRequestDTO request);

    FilmResponseDTO getFilmById(Short id);

    Page<FilmResponseDTO> getAllFilms(int page, int size);

    FilmResponseDTO updateFilm(Short id, FilmRequestDTO request);

    FilmResponseDTO patchFilm(Short id, FilmPatchDTO request);

    public Page<FilmResponseDTO> searchFilms(
            String title,
            Integer year,
            String category,
            String actor,
            int page,
            int size) ;
}