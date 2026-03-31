package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Short> {

    List<Actor> findByFirstNameContainingIgnoreCase(String firstName);

    List<Actor> findByLastNameContainingIgnoreCase(String lastName);

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


    @Query("""
            select distinct a
            from Actor a
            where lower(concat(a.firstName, ' ', a.lastName)) like lower(concat('%', :name, '%'))
               or lower(a.firstName) like lower(concat('%', :name, '%'))
               or lower(a.lastName) like lower(concat('%', :name, '%'))
            """)
    Page<Actor> searchByName(@Param("name") String name, Pageable pageable);

    Optional<Actor> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);
}

