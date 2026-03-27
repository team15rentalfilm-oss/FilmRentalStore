package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.FilmActor;
import com.iem.FilmRentalStore.entity.FilmActorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorId> {
}
