package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.FilmActorDTO;
import java.util.List;

public interface FilmActorService {

    List<FilmActorDTO> getAll();

    FilmActorDTO create(FilmActorDTO dto);

    void delete(int actorId, int filmId);
}