package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Actor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Short> {

    // Search by first name (partial)
    List<Actor> findByFirstNameContainingIgnoreCase(String firstName);

    // Search by last name (partial)
    List<Actor> findByLastNameContainingIgnoreCase(String lastName);

    // Combined search (best one)
    List<Actor> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName);

    @Query("""
            select distinct a
            from Actor a
            where lower(concat(a.firstName, ' ', a.lastName)) like lower(concat('%', :name, '%'))
               or lower(a.firstName) like lower(concat('%', :name, '%'))
               or lower(a.lastName) like lower(concat('%', :name, '%'))
            order by a.firstName, a.lastName
            """)
    List<Actor> searchByName(@Param("name") String name);

    @Query("""
            select distinct a
            from Actor a
            join a.filmActors fa
            join fa.film f
            where lower(f.title) like lower(concat('%', :title, '%'))
            order by a.firstName, a.lastName
            """)
    List<Actor> findDistinctByFilmTitleContaining(@Param("title") String title);

    Optional<Actor> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);

}

