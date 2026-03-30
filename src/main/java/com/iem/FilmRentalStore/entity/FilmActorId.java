package com.iem.FilmRentalStore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FilmActorId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "actor_id")
    private Short actorId;

    @Column(name = "film_id")
    private Short filmId;
}
