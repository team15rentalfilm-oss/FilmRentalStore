package com.iem.FilmRentalStore.mapper;

import com.iem.FilmRentalStore.dto.film.*;
import com.iem.FilmRentalStore.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilmMapper {

    // CREATE
    public static Film toEntity(FilmRequestDTO dto,
                                Language language,
                                Set<Category> categories,
                                Set<Actor> actors) {

        Film film = new Film();

        updateEntity(film, dto, language, categories, actors);

        return film;
    }

    // UPDATE (FULL)
    public static void updateEntity(Film film,
                                    FilmRequestDTO dto,
                                    Language language,
                                    Set<Category> categories,
                                    Set<Actor> actors) {

        film.setTitle(dto.getTitle());
        film.setDescription(dto.getDescription());
        film.setReleaseYear(dto.getReleaseYear());

        film.setLanguage(language);

        film.setRentalDuration(dto.getRentalDuration());
        film.setRentalRate(dto.getRentalRate());
        film.setLength(dto.getLength());
        film.setReplacementCost(dto.getReplacementCost());

        film.setRating(dto.getRating());
        film.setSpecialFeatures(
                dto.getSpecialFeatures() == null ? null :
                        String.join(",", dto.getSpecialFeatures())
        );

        film.setCategories(categories);
        film.setActors(actors);
    }

    // PATCH
    public static void patchEntity(Film film, FilmPatchDTO dto) {

        if (dto.getTitle() != null) film.setTitle(dto.getTitle());
        if (dto.getDescription() != null) film.setDescription(dto.getDescription());
        if (dto.getReleaseYear() != null) film.setReleaseYear(dto.getReleaseYear());
        if (dto.getRating() != null) film.setRating(dto.getRating());
    }

    // RESPONSE
    public static FilmResponseDTO toResponseDTO(Film film) {

        FilmResponseDTO dto = new FilmResponseDTO();

        dto.setFilmId(film.getFilmId());
        dto.setTitle(film.getTitle());
        dto.setDescription(film.getDescription());
        dto.setReleaseYear(film.getReleaseYear());
        dto.setLastUpdate(film.getLastUpdate());

        dto.setLanguage(LanguageMapper.toDTO(film.getLanguage()));

        dto.setRentalDuration(film.getRentalDuration());
        dto.setRentalRate(film.getRentalRate());
        dto.setLength(film.getLength());
        dto.setReplacementCost(film.getReplacementCost());

        dto.setRating(film.getRating());
        dto.setSpecialFeatures(
                film.getSpecialFeatures() == null ? Set.of() :
                        Set.of(film.getSpecialFeatures().split(","))
        );

        dto.setCategories(
                film.getCategories() == null ? List.of() :
                        film.getCategories().stream()
                                .map(CategoryMapper::toDTO)
                                .toList()
        );

        dto.setActors(
                film.getActors() == null ? List.of() :
                        film.getActors().stream()
                                .map(ActorMapper::toDTO)
                                .toList()
        );

        return dto;
    }

    // LIGHTWEIGHT
    public static FilmDTO toDTO(Film film) {
        FilmDTO dto = new FilmDTO();
        dto.setFilmId(film.getFilmId());
        dto.setTitle(film.getTitle());
        dto.setRating(film.getRating());
        return dto;
    }
}
