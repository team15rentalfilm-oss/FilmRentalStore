package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.actor.ActorRequestDTO;
import com.iem.FilmRentalStore.dto.actor.ActorResponseDTO;
import com.iem.FilmRentalStore.entity.Actor;
import com.iem.FilmRentalStore.mapper.ActorMapper;
import com.iem.FilmRentalStore.repository.ActorRepository;
import com.iem.FilmRentalStore.service.ActorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    @Override
    public ActorResponseDTO createActor(ActorRequestDTO request) {
        Actor actor = ActorMapper.toEntity(request);
        Actor saved = actorRepository.save(actor);
        return ActorMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ActorResponseDTO getActorById(Short id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Actor not found with id: " + id));

        return ActorMapper.toResponseDTO(actor);
    }

    // ✅ PAGINATED GET ALL
    @Override
    @Transactional(readOnly = true)
    public Page<ActorResponseDTO> getAllActors(Pageable pageable) {
        return actorRepository.findAll(pageable)
                .map(ActorMapper::toResponseDTO);
    }

    @Override
    public ActorResponseDTO updateActor(Short id, ActorRequestDTO request) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Actor not found with id: " + id));

        actor.setFirstName(request.getFirstName());
        actor.setLastName(request.getLastName());

        Actor updated = actorRepository.save(actor);
        return ActorMapper.toResponseDTO(updated);
    }

    // ✅ PAGINATED SEARCH
    @Override
    @Transactional(readOnly = true)
    public Page<ActorResponseDTO> searchActors(String name, Pageable pageable) {
        return actorRepository
                .searchByName(name == null ? "" : name.trim(), pageable)
                .map(ActorMapper::toResponseDTO);
    }

    private String normalize(String value) {
        return value == null ? null : value.trim().toLowerCase();
    }

    private Set<Actor> getOrCreateActors(Set<String> names) {
        return names.stream()
                .map(fullName -> {

                    String[] parts = fullName.trim().split(" ", 2);

                    String firstName = normalize(parts[0]);
                    String lastName = parts.length > 1 ? normalize(parts[1]) : "";

                    return actorRepository
                            .findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName)
                            .orElseGet(() -> {
                                try {
                                    Actor actor = new Actor();
                                    actor.setFirstName(firstName);
                                    actor.setLastName(lastName);
                                    return actorRepository.save(actor);
                                } catch (Exception e) {
                                    return actorRepository
                                            .findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName)
                                            .orElseThrow(() -> new RuntimeException(e));
                                }
                            });
                })
                .collect(Collectors.toSet());
    }
}