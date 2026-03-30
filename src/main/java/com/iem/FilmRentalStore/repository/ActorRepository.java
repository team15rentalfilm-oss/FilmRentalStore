package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Integer> {

    // Search by first name (partial)
    List<Actor> findByFirstNameContainingIgnoreCase(String firstName);

    // Search by last name (partial)
    List<Actor> findByLastNameContainingIgnoreCase(String lastName);

    // Combined search (best one)
    List<Actor> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName);

    Optional<Actor> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);

}

