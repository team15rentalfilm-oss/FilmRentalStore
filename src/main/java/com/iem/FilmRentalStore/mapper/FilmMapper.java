package com.iem.FilmRentalStore.mapper;

import com.iem.FilmRentalStore.dto.film.*;
import com.iem.FilmRentalStore.entity.*;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilmMapper {

    //RequestDTO → Entity
    public static Film toEntity(FilmRequestDTO dto,
                                Language language,
                                Set<Category> categories,
                                Set<Actor> actors) {

        Film film = new Film();

        film.setTitle(dto.getTitle());
        film.setDescription(dto.getDescription());
        film.setReleaseYear(dto.getReleaseYear());

        film.setLanguage(language);

        film.setRentalDuration(dto.getRentalDuration());
        film.setRentalRate(dto.getRentalRate());
        film.setLength(dto.getLength());
        film.setReplacementCost(dto.getReplacementCost());

        film.setRating(dto.getRating());
        film.setSpecialFeatures(dto.getSpecialFeatures());

        film.setCategories(categories);
        film.setActors(actors);

        return film;
    }

    //Entity → ResponseDTO
    public static FilmResponseDTO toResponseDTO(Film film) {
        FilmResponseDTO dto = new FilmResponseDTO();

        dto.setFilmId(film.getFilmId());
        dto.setTitle(film.getTitle());
        dto.setDescription(film.getDescription());
        dto.setReleaseYear(film.getReleaseYear());

        dto.setLanguage(LanguageMapper.toDTO(film.getLanguage()));

        dto.setRentalDuration(film.getRentalDuration());
        dto.setRentalRate(film.getRentalRate());
        dto.setLength(film.getLength());
        dto.setReplacementCost(film.getReplacementCost());

        dto.setRating(film.getRating());
        dto.setSpecialFeatures(film.getSpecialFeatures());

        // Categories
        dto.setCategories(
                film.getCategories()
                        .stream()
                        .map(CategoryMapper::toDTO)
                        .collect(Collectors.toList())
        );

        // Actors
        dto.setActors(
                film.getActors()
                        .stream()
                        .map(ActorMapper::toDTO)
                        .collect(Collectors.toList())
        );

        return dto;
    }

    //Entity → Lightweight DTO
    public static FilmDTO toDTO(Film film) {
        FilmDTO dto = new FilmDTO();

        dto.setTitle(film.getTitle());
        dto.setRating(film.getRating());

        return dto;
    }
}