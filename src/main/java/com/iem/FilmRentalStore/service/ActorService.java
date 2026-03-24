package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.ActorDTO;
import java.util.List;

public interface ActorService {

    List<ActorDTO> getAll();

    ActorDTO getById(int id);

    List<ActorDTO> getByFirstName(String name);

    ActorDTO create(ActorDTO dto);

    ActorDTO update(int id, ActorDTO dto);

    ActorDTO patch(int id, ActorDTO dto);

    void delete(int id);
}
