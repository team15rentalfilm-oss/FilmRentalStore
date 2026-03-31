package com.iem.FilmRentalStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "film_actor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"actor", "film"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FilmActor {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private FilmActorId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("actorId")
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("filmId")
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    public FilmActor(Film film, Actor actor) {
        this.id = new FilmActorId(
                actor != null ? actor.getActorId() : null,
                film != null ? film.getFilmId() : null
        );
        this.actor = actor;
        this.film = film;
    }

    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = new FilmActorId();
        }
        this.id.setActorId(actor != null ? actor.getActorId() : null);
        this.id.setFilmId(film != null ? film.getFilmId() : null);
        this.lastUpdate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        if (this.id == null) {
            this.id = new FilmActorId();
        }
        this.id.setActorId(actor != null ? actor.getActorId() : null);
        this.id.setFilmId(film != null ? film.getFilmId() : null);
        this.lastUpdate = LocalDateTime.now();
    }
}
