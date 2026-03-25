package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.FilmActorDTO;
import com.iem.FilmRentalStore.dto.FilmActorDetailsDTO;
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

    @Override
    public List<FilmActorDTO> getAll() {
        return repo.findAll().stream().map(fa -> {
            FilmActorDTO dto = new FilmActorDTO();
            dto.setActorId(fa.getId().getActorId().intValue());
            dto.setFilmId(fa.getId().getFilmId().intValue());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public FilmActorDTO create(FilmActorDTO dto) {
        FilmActorId id = new FilmActorId();
        id.setActorId(dto.getActorId().shortValue());
        id.setFilmId(dto.getFilmId().shortValue());

        FilmActor fa = new FilmActor();
        fa.setId(id);

        repo.save(fa);
        return dto;
    }

    @Override
    public void delete(int actorId, int filmId) {
        FilmActorId id = new FilmActorId();
        id.setActorId((short) actorId);
        id.setFilmId((short) filmId);

        repo.deleteById(id);
    }

    @Override
    public List<FilmActorDetailsDTO> getFilmActorDetails() {
        return repo.findAll().stream()
                .map(fa -> new FilmActorDetailsDTO(
                        fa.getId().getActorId().intValue(),
                        fa.getActor() != null ? fa.getActor().getFirstName() : null,
                        fa.getActor() != null ? fa.getActor().getLastName() : null,
                        fa.getId().getFilmId().intValue(),
                        fa.getFilm() != null ? fa.getFilm().getTitle() : null
                ))
                .collect(Collectors.toList());
    }
}
