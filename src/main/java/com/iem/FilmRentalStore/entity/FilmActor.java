package com.iem.FilmRentalStore.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "film_actor")
@Data
public class FilmActor {

    @EmbeddedId
    private FilmActorId id;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}