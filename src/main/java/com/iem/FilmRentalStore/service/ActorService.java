package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.actor.ActorDTO;
import com.iem.FilmRentalStore.dto.actor.ActorRequestDTO;

import java.util.List;

public interface ActorService {

    ActorDTO createActor(ActorRequestDTO request);

    ActorDTO getActorById(Integer id);

    List<ActorDTO> getAllActors();

    ActorDTO updateActor(Integer id, ActorRequestDTO request);
}