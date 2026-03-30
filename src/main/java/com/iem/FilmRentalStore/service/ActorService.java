package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.actor.ActorDTO;
import com.iem.FilmRentalStore.dto.actor.ActorRequestDTO;
import com.iem.FilmRentalStore.dto.actor.ActorResponseDTO;

import java.util.List;

public interface ActorService {

    ActorResponseDTO createActor(ActorRequestDTO request);

    ActorResponseDTO getActorById(Integer id);

    List<ActorResponseDTO> getAllActors();

    ActorResponseDTO updateActor(Integer id, ActorRequestDTO request);

    List<ActorResponseDTO> searchActors(String name);
}