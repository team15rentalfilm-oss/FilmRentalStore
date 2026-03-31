package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.actor.ActorDTO;
import com.iem.FilmRentalStore.dto.actor.ActorRequestDTO;
import com.iem.FilmRentalStore.dto.actor.ActorResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ActorService {

    ActorResponseDTO createActor(ActorRequestDTO request);

    ActorResponseDTO getActorById(Short id);

    Page<ActorResponseDTO> getAllActors(Pageable pageable);   // ✅ changed

    ActorResponseDTO updateActor(Short id, ActorRequestDTO request);

    Page<ActorResponseDTO> searchActors(String name, Pageable pageable); // ✅ changed
}