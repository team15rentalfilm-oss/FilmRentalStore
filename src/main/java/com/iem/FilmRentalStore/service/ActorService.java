package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.actor.ActorDTO;
import com.iem.FilmRentalStore.dto.actor.ActorRequestDTO;
import com.iem.FilmRentalStore.dto.actor.ActorResponseDTO;

import java.util.List;

public interface ActorService {

    ActorResponseDTO createActor(ActorRequestDTO request);

    ActorResponseDTO getActorById(Short id);

    List<ActorResponseDTO> getAllActors();

    ActorResponseDTO updateActor(Short id, ActorRequestDTO request);

    List<ActorResponseDTO> searchActors(String name);
}
