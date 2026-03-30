package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.FilmActor;
import com.iem.FilmRentalStore.entity.FilmActorId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorId> {

    List<FilmActor> findByFilm_FilmId(Short filmId);

    List<FilmActor> findByActor_ActorId(Short actorId);
}
