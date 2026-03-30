package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.actor.ActorResponseDTO;
import com.iem.FilmRentalStore.dto.film.FilmResponseDTO;

import java.util.List;

public interface FilmActorService {

    List<FilmResponseDTO> getFilmsByActor(String name);

    List<ActorResponseDTO> getActorsByFilm(String title);
}
