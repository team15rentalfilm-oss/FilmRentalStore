package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.ActorDTO;
import com.iem.FilmRentalStore.entity.Actor;
import com.iem.FilmRentalStore.repository.ActorRepository;
import com.iem.FilmRentalStore.service.ActorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository repo;

    public ActorServiceImpl(ActorRepository repo) {
        this.repo = repo;
    }

    private ActorDTO mapToDTO(Actor actor) {
        ActorDTO dto = new ActorDTO();
        // dto.setActorId(actor.getActorId()); // keep only if ActorDTO has actorId
        dto.setFirstName(actor.getFirstName());
        dto.setLastName(actor.getLastName());
        return dto;
    }

    private Actor mapToEntity(ActorDTO dto) {
        Actor actor = new Actor();
        // actor.setActorId(dto.getActorId()); // keep only if ActorDTO has actorId
        actor.setFirstName(dto.getFirstName());
        actor.setLastName(dto.getLastName());
        return actor;
    }

    @Override
    public List<ActorDTO> getAll() {
        return repo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ActorDTO getById(Short id) {
        return mapToDTO(repo.findById(id).orElseThrow());
    }

    @Override
    public List<ActorDTO> getByFirstName(String name) {
        return repo.findByFirstName(name).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ActorDTO create(ActorDTO dto) {
        return mapToDTO(repo.save(mapToEntity(dto)));
    }

    @Override
    public ActorDTO update(Short id, ActorDTO dto) {
        Actor actor = mapToEntity(dto);
        actor.setActorId(id);
        return mapToDTO(repo.save(actor));
    }

    @Override
    public ActorDTO patch(Short id, ActorDTO dto) {
        Actor existing = repo.findById(id).orElseThrow();

        if (dto.getFirstName() != null) {
            existing.setFirstName(dto.getFirstName());
        }

        if (dto.getLastName() != null) {
            existing.setLastName(dto.getLastName());
        }

        return mapToDTO(repo.save(existing));
    }

    @Override
    public void delete(Short id) {
        repo.deleteById(id);
    }
}