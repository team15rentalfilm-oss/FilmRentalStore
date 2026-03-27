package com.iem.FilmRentalStore.service;

import java.util.List;

public interface FilmActorService {

    List<FilmActorDTO> getAll();

    FilmActorDTO create(FilmActorDTO dto);

    void delete(int actorId, int filmId);

    List<FilmActorDetailsDTO> getFilmActorDetails();
}