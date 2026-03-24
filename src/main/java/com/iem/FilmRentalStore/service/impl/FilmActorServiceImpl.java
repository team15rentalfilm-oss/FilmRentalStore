package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.FilmActorDTO;
import com.iem.FilmRentalStore.entity.FilmActor;
import com.iem.FilmRentalStore.entity.FilmActorId;
import com.iem.FilmRentalStore.repository.FilmActorRepository;
import com.iem.FilmRentalStore.service.FilmActorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmActorServiceImpl implements FilmActorService {

    private final FilmActorRepository repo;

    public FilmActorServiceImpl(FilmActorRepository repo) {
        this.repo = repo;
    }

    public List<FilmActorDTO> getAll() {
        return repo.findAll().stream().map(fa -> {
            FilmActorDTO dto = new FilmActorDTO();
            dto.setActorId(fa.getId().getActorId());
            dto.setFilmId(fa.getId().getFilmId());
            return dto;
        }).collect(Collectors.toList());
    }

    public FilmActorDTO create(FilmActorDTO dto) {
        FilmActorId id = new FilmActorId();
        id.setActorId(dto.getActorId());
        id.setFilmId(dto.getFilmId());

        FilmActor fa = new FilmActor();
        fa.setId(id);

        repo.save(fa);
        return dto;
    }

    public void delete(int actorId, int filmId) {
        FilmActorId id = new FilmActorId();
        id.setActorId(actorId);
        id.setFilmId(filmId);

        repo.deleteById(id);
    }
}