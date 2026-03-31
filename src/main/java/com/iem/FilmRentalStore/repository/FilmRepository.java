package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Short> {

    List<Film> findByTitleContainingIgnoreCase(String title);

    List<Film> findTop10ByTitleContainingIgnoreCaseOrderByTitleAsc(String title);

    List<Film> findByCategories_NameIgnoreCase(String categoryName);
    List<Film> findByTitleContainingIgnoreCaseAndReleaseYear(String title, Integer releaseYear);

    List<Film> findByReleaseYear(Integer releaseYear);

    @EntityGraph(attributePaths = {"categories", "filmActors.actor", "specialFeatures"})
    Optional<Film> findWithRelationsByFilmId(Short id);

    @EntityGraph(attributePaths = {"categories", "filmActors.actor", "specialFeatures"})
    Page<Film> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"categories", "filmActors.actor", "specialFeatures"})
    @Query(
            value = """
                    select distinct f
                    from Film f
                    left join f.categories c
                    left join f.filmActors fa
                    left join fa.actor a
                    where (:title is null or trim(:title) = '' or lower(f.title) like lower(concat('%', :title, '%')))
                      and (:year is null or f.releaseYear = :year)
                      and (:category is null or trim(:category) = '' or lower(c.name) like lower(concat('%', :category, '%')))
                      and (
                            :actor is null or trim(:actor) = ''
                            or lower(concat(a.firstName, ' ', a.lastName)) like lower(concat('%', :actor, '%'))
                            or lower(a.firstName) like lower(concat('%', :actor, '%'))
                            or lower(a.lastName) like lower(concat('%', :actor, '%'))
                      )
                    """,
            countQuery = """
                    select count(distinct f)
                    from Film f
                    left join f.categories c
                    left join f.filmActors fa
                    left join fa.actor a
                    where (:title is null or trim(:title) = '' or lower(f.title) like lower(concat('%', :title, '%')))
                      and (:year is null or f.releaseYear = :year)
                      and (:category is null or trim(:category) = '' or lower(c.name) like lower(concat('%', :category, '%')))
                      and (
                            :actor is null or trim(:actor) = ''
                            or lower(concat(a.firstName, ' ', a.lastName)) like lower(concat('%', :actor, '%'))
                            or lower(a.firstName) like lower(concat('%', :actor, '%'))
                            or lower(a.lastName) like lower(concat('%', :actor, '%'))
                      )
                    """
    )
    Page<Film> searchFilms(
            @Param("title") String title,
            @Param("year") Integer year,
            @Param("category") String category,
            @Param("actor") String actor,
            Pageable pageable
    );

    @Query("""
            select distinct f
            from Film f
            join f.filmActors fa
            join fa.actor a
            where lower(concat(a.firstName, ' ', a.lastName)) like lower(concat('%', :name, '%'))
               or lower(a.firstName) like lower(concat('%', :name, '%'))
               or lower(a.lastName) like lower(concat('%', :name, '%'))
            order by f.title
            """)
    List<Film> findDistinctByActorNameContaining(@Param("name") String name);
}



