package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
    List<Actor> findByFirstName(String firstName);
}
