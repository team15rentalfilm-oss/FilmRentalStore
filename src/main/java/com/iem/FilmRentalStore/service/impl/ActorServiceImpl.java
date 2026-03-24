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
        dto.setActorId(actor.getActorId());
        dto.setFirstName(actor.getFirstName());
        dto.setLastName(actor.getLastName());
        return dto;
    }

    private Actor mapToEntity(ActorDTO dto) {
        Actor actor = new Actor();
        actor.setActorId(dto.getActorId());
        actor.setFirstName(dto.getFirstName());
        actor.setLastName(dto.getLastName());
        return actor;
    }

    public List<ActorDTO> getAll() {
        return repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public ActorDTO getById(int id) {
        return mapToDTO(repo.findById(id).orElseThrow());
    }

    public List<ActorDTO> getByFirstName(String name) {
        return repo.findByFirstName(name).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public ActorDTO create(ActorDTO dto) {
        return mapToDTO(repo.save(mapToEntity(dto)));
    }

    public ActorDTO update(int id, ActorDTO dto) {
        Actor actor = mapToEntity(dto);
        actor.setActorId(id);
        return mapToDTO(repo.save(actor));
    }

    public ActorDTO patch(int id, ActorDTO dto) {
        Actor existing = repo.findById(id).orElseThrow();

        if (dto.getFirstName() != null)
            existing.setFirstName(dto.getFirstName());

        if (dto.getLastName() != null)
            existing.setLastName(dto.getLastName());

        return mapToDTO(repo.save(existing));
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
