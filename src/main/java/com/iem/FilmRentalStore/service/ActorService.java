package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.ActorDTO;
import java.util.List;

public interface ActorService {

    List<ActorDTO> getAll();

    ActorDTO getById(Short id);

    List<ActorDTO> getByFirstName(String name);

    ActorDTO create(ActorDTO dto);

    ActorDTO update(Short id, ActorDTO dto);

    ActorDTO patch(Short id, ActorDTO dto);

    void delete(Short id);
}