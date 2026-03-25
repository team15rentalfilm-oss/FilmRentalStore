package com.iem.FilmRentalStore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class FilmActorId implements Serializable {

    @Column(name = "actor_id", columnDefinition = "SMALLINT UNSIGNED")
    private Integer actorId;

    @Column(name = "film_id", columnDefinition = "SMALLINT UNSIGNED")
    private Integer filmId;
}