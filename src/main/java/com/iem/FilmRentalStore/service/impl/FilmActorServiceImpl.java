package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.FilmActorDTO;
import com.iem.FilmRentalStore.dto.FilmActorDetailsDTO;
import com.iem.FilmRentalStore.entity.Actor;
import com.iem.FilmRentalStore.entity.Film;
import com.iem.FilmRentalStore.entity.FilmActor;
import com.iem.FilmRentalStore.entity.FilmActorId;
import com.iem.FilmRentalStore.repository.ActorRepository;
import com.iem.FilmRentalStore.repository.FilmActorRepository;
import com.iem.FilmRentalStore.repository.FilmRepository;
import com.iem.FilmRentalStore.service.FilmActorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FilmActorServiceImpl implements FilmActorService {

    private final FilmActorRepository filmActorRepository;
    private final ActorRepository actorRepository;
    private final FilmRepository filmRepository;

    public FilmActorServiceImpl(FilmActorRepository filmActorRepository,
                                ActorRepository actorRepository,
                                FilmRepository filmRepository) {
        this.filmActorRepository = filmActorRepository;
        this.actorRepository = actorRepository;
        this.filmRepository = filmRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilmActorDTO> getAll() {
        return filmActorRepository.findAll()
                .stream()
                .map(entity -> {
                    FilmActorDTO dto = new FilmActorDTO();
                    dto.setActorId(entity.getId().getActorId());
                    dto.setFilmId(entity.getId().getFilmId());
                    return dto;
                })
                .toList();
    }

    @Override
    public FilmActorDTO create(FilmActorDTO dto) {
        Actor actor = actorRepository.findById(dto.getActorId())
                .orElseThrow(() -> new EntityNotFoundException("Actor not found with id " + dto.getActorId()));

        Film film = filmRepository.findById(dto.getFilmId())
                .orElseThrow(() -> new EntityNotFoundException("Film not found with id " + dto.getFilmId()));

        FilmActorId id = new FilmActorId(dto.getActorId(), dto.getFilmId());

        if (filmActorRepository.existsById(id)) {
            throw new IllegalArgumentException("FilmActor already exists for actorId=" + dto.getActorId()
                    + " and filmId=" + dto.getFilmId());
        }

        FilmActor filmActor = new FilmActor();
        filmActor.setId(id);
        filmActor.setActor(actor);
        filmActor.setFilm(film);

        filmActorRepository.save(filmActor);
        return dto;
    }

    @Override
    public void delete(Short actorId, Short filmId) {
        FilmActorId id = new FilmActorId(actorId, filmId);

        if (!filmActorRepository.existsById(id)) {
            throw new EntityNotFoundException("FilmActor not found for actorId=" + actorId + " and filmId=" + filmId);
        }

        filmActorRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilmActorDetailsDTO> getFilmActorDetails() {
        return filmActorRepository.findAllDetails();
    }
}