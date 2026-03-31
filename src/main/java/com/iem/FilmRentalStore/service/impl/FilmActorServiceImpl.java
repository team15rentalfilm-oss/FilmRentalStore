package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.actor.ActorResponseDTO;
import com.iem.FilmRentalStore.dto.film.FilmResponseDTO;
import com.iem.FilmRentalStore.entity.Film;
import com.iem.FilmRentalStore.repository.ActorRepository;
import com.iem.FilmRentalStore.repository.FilmRepository;
import com.iem.FilmRentalStore.service.FilmActorService;
import com.iem.FilmRentalStore.mapper.ActorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FilmActorServiceImpl implements FilmActorService {

    private final FilmRepository filmRepository;
    private final ActorRepository actorRepository;

    @Override
    public List<FilmResponseDTO> getFilmsByActor(String name) {
        String query = name == null ? "" : name.trim();
        return filmRepository.findDistinctByActorNameContaining(query).stream()
                .map(this::toFilmResponse)
                .toList();
    }

    @Override
    public List<ActorResponseDTO> getActorsByFilm(String title) {
        String query = title == null ? "" : title.trim();
        return actorRepository.findDistinctByFilmTitleContaining(query).stream()
                .map(ActorMapper::toResponseDTO)
                .toList();
    }

    private FilmResponseDTO toFilmResponse(Film film) {
        FilmResponseDTO response = new FilmResponseDTO();
        response.setFilmId(film.getFilmId());
        response.setTitle(film.getTitle());
        return response;
    }
}
