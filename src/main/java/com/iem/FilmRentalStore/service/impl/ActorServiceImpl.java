package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.actor.ActorDTO;
import com.iem.FilmRentalStore.dto.actor.ActorRequestDTO;
import com.iem.FilmRentalStore.dto.actor.ActorResponseDTO;
import com.iem.FilmRentalStore.entity.Actor;
import com.iem.FilmRentalStore.mapper.ActorMapper;
import com.iem.FilmRentalStore.repository.ActorRepository;
import com.iem.FilmRentalStore.service.ActorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    @Override
    public ActorResponseDTO createActor(ActorRequestDTO request) {
        Actor actor = ActorMapper.toEntity(request);
        Actor saved = actorRepository.save(actor);
        return actorMapper.toResponseDTO(saved);
    }

    @Override
    public ActorResponseDTO getActorById(Integer id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Actor not found with id: " + id));

        return actorMapper.toResponseDTO(actor);
    }

    @Override
    public List<ActorResponseDTO> getAllActors() {
        return actorRepository.findAll()
                .stream()
                .map(ActorMapper::toResponseDTO)
                .toList();
    }

    @Override
    public ActorResponseDTO updateActor(Integer id, ActorRequestDTO request) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Actor not found with id: " + id));

        actor.setFirstName(request.getFirstName());
        actor.setLastName(request.getLastName());

        Actor updated = actorRepository.save(actor);
        return actorMapper.toResponseDTO(updated);
    }

    @Override
    public List<ActorResponseDTO> searchActors(String name) {
        return actorRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name)
                .stream()
                .map(ActorMapper::toResponseDTO)
                .toList();
    }
}