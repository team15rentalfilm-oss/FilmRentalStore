package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.FilmActorDTO;
import com.iem.FilmRentalStore.dto.FilmActorDetailsDTO;

import java.util.List;

public interface FilmActorService {

    List<FilmActorDTO> getAll();

    FilmActorDTO create(FilmActorDTO dto);

    void delete(Short actorId, Short filmId);

    List<FilmActorDetailsDTO> getFilmActorDetails();
}