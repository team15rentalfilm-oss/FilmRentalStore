package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.dto.FilmActorDetailsDTO;
import com.iem.FilmRentalStore.entity.FilmActor;
import com.iem.FilmRentalStore.entity.FilmActorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorId> {

    @Query("""
           select new com.iem.FilmRentalStore.dto.FilmActorDetailsDTO(
               fa.actor.actorId,
               fa.actor.firstName,
               fa.actor.lastName,
               fa.film.filmId,
               fa.film.title,
               fa.lastUpdate
           )
           from FilmActor fa
           """)
    List<FilmActorDetailsDTO> findAllDetails();
}