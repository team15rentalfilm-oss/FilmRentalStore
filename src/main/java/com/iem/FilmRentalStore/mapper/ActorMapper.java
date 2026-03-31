package com.iem.FilmRentalStore.mapper;


import com.iem.FilmRentalStore.dto.actor.*;

import com.iem.FilmRentalStore.entity.Actor;
import org.springframework.stereotype.Component;

@Component
public class ActorMapper {

    public static Actor toEntity(ActorRequestDTO dto) {
        Actor actor = new Actor();
        actor.setFirstName(dto.getFirstName());
        actor.setLastName(dto.getLastName());
        return actor;
    }

    public static ActorResponseDTO toResponseDTO(Actor actor) {
        ActorResponseDTO dto = new ActorResponseDTO();
        dto.setActorId(actor.getActorId());
        dto.setFirstName(actor.getFirstName());
        dto.setLastName(actor.getLastName());
        return dto;
    }

    public static ActorDTO toDTO(Actor actor) {
        ActorDTO dto = new ActorDTO();
        dto.setActorId(actor.getActorId());
        dto.setFirstName(actor.getFirstName());
        dto.setLastName(actor.getLastName());
        return dto;
    }
}
