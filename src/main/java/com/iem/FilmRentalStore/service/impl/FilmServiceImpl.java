package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.FilmDTO;
import com.iem.FilmRentalStore.dto.FilmTextDTO;
import com.iem.FilmRentalStore.entity.Category;
import com.iem.FilmRentalStore.entity.Film;
import com.iem.FilmRentalStore.repository.CategoryRepository;
import com.iem.FilmRentalStore.repository.FilmRepository;
import com.iem.FilmRentalStore.service.FilmService;
import com.iem.FilmRentalStore.service.FilmTextService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final CategoryRepository categoryRepository;
    private final FilmTextService filmTextService;

    public FilmServiceImpl(FilmRepository filmRepository,
                           CategoryRepository categoryRepository,
                           FilmTextService filmTextService) {
        this.filmRepository = filmRepository;
        this.categoryRepository = categoryRepository;
        this.filmTextService = filmTextService;
    }

    @Override
    public FilmDTO createFilm(FilmDTO filmDTO) {
        Film film = new Film();
        mapDtoToEntity(filmDTO, film);

        Film saved = filmRepository.save(film);

        // Category handling
        if (filmDTO.getCategoryIds() != null && !filmDTO.getCategoryIds().isEmpty()) {
            Set<Category> categories = categoryRepository.findAllById(filmDTO.getCategoryIds())
                    .stream()
                    .collect(Collectors.toSet());
            saved.setCategories(categories);
            saved = filmRepository.save(saved);
        }

        // Auto-create film_text
        String description = saved.getDescription() != null ? saved.getDescription() : "";

        filmTextService.createFilmText(
                new FilmTextDTO(
                        saved.getFilmId(),
                        saved.getTitle(),
                        description
                )
        );

        return mapEntityToDto(saved);
    }

    @Override
    public FilmDTO getFilmById(Short id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film not found with id: " + id));
        return mapEntityToDto(film);
    }

    @Override
    public List<FilmDTO> getAllFilms() {
        return filmRepository.findAll()
                .stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    @Override
    public FilmDTO updateFilm(Short id, FilmDTO filmDTO) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film not found with id: " + id));

        mapDtoToEntity(filmDTO, film);

        if (filmDTO.getCategoryIds() != null) {
            film.getCategories().clear();

            if (!filmDTO.getCategoryIds().isEmpty()) {
                Set<Category> categories = categoryRepository.findAllById(filmDTO.getCategoryIds())
                        .stream()
                        .collect(Collectors.toSet());
                film.getCategories().addAll(categories);
            }
        }

        return mapEntityToDto(filmRepository.save(film));
    }

    @Override
    public void deleteFilm(Short id) {
        if (!filmRepository.existsById(id)) {
            throw new RuntimeException("Film not found with id: " + id);
        }
        filmRepository.deleteById(id);
    }

    @Override
    public List<FilmDTO> searchFilms(String title, Integer year) {
        List<Film> films;

        if (title != null && !title.isBlank() && year != null) {
            films = filmRepository.findByTitleContainingIgnoreCaseAndReleaseYear(title, year);
        } else if (title != null && !title.isBlank()) {
            films = filmRepository.findByTitleContainingIgnoreCase(title);
        } else if (year != null) {
            films = filmRepository.findByReleaseYear(year);
        } else {
            films = filmRepository.findAll();
        }

        return films.stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    @Override
    public FilmDTO patchFilm(Short id, FilmDTO filmDTO) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film not found with id: " + id));

        if (filmDTO.getTitle() != null) film.setTitle(filmDTO.getTitle());
        if (filmDTO.getDescription() != null) film.setDescription(filmDTO.getDescription());
        if (filmDTO.getReleaseYear() != null) film.setReleaseYear(filmDTO.getReleaseYear());
        if (filmDTO.getLanguageId() != null) film.setLanguageId(filmDTO.getLanguageId());
        if (filmDTO.getOriginalLanguageId() != null) film.setOriginalLanguageId(filmDTO.getOriginalLanguageId());
        if (filmDTO.getRentalDuration() != null) film.setRentalDuration(filmDTO.getRentalDuration());
        if (filmDTO.getRentalRate() != null) film.setRentalRate(filmDTO.getRentalRate());
        if (filmDTO.getLength() != null) film.setLength(filmDTO.getLength());
        if (filmDTO.getReplacementCost() != null) film.setReplacementCost(filmDTO.getReplacementCost());
        if (filmDTO.getRating() != null) film.setRating(filmDTO.getRating());
        if (filmDTO.getSpecialFeatures() != null) film.setSpecialFeatures(filmDTO.getSpecialFeatures());

        if (filmDTO.getCategoryIds() != null) {
            film.getCategories().clear();

            if (!filmDTO.getCategoryIds().isEmpty()) {
                Set<Category> categories = categoryRepository.findAllById(filmDTO.getCategoryIds())
                        .stream()
                        .collect(Collectors.toSet());
                film.getCategories().addAll(categories);
            }
        }

        return mapEntityToDto(filmRepository.save(film));
    }

    // ---------------- HELPER METHODS ----------------

    private void mapDtoToEntity(FilmDTO dto, Film film) {
        if (dto.getTitle() != null) film.setTitle(dto.getTitle());
        if (dto.getDescription() != null) film.setDescription(dto.getDescription());
        if (dto.getReleaseYear() != null) film.setReleaseYear(dto.getReleaseYear());
        if (dto.getLanguageId() != null) film.setLanguageId(dto.getLanguageId());
        if (dto.getOriginalLanguageId() != null) film.setOriginalLanguageId(dto.getOriginalLanguageId());
        if (dto.getRentalDuration() != null) film.setRentalDuration(dto.getRentalDuration());
        if (dto.getRentalRate() != null) film.setRentalRate(dto.getRentalRate());
        if (dto.getLength() != null) film.setLength(dto.getLength());
        if (dto.getReplacementCost() != null) film.setReplacementCost(dto.getReplacementCost());
        if (dto.getRating() != null) film.setRating(dto.getRating());
        if (dto.getSpecialFeatures() != null) film.setSpecialFeatures(dto.getSpecialFeatures());
    }

    private FilmDTO mapEntityToDto(Film film) {
        FilmDTO dto = new FilmDTO();
        dto.setId(film.getFilmId());
        dto.setTitle(film.getTitle());
        dto.setDescription(film.getDescription());
        dto.setReleaseYear(film.getReleaseYear());
        dto.setLanguageId(film.getLanguageId());
        dto.setOriginalLanguageId(film.getOriginalLanguageId());
        dto.setRentalDuration(film.getRentalDuration());
        dto.setRentalRate(film.getRentalRate());
        dto.setLength(film.getLength());
        dto.setReplacementCost(film.getReplacementCost());
        dto.setRating(film.getRating());
        dto.setSpecialFeatures(film.getSpecialFeatures());

        if (film.getCategories() != null) {
            dto.setCategoryIds(
                    film.getCategories().stream()
                            .map(Category::getId)
                            .collect(Collectors.toSet())
            );
        }

        return dto;
    }
}