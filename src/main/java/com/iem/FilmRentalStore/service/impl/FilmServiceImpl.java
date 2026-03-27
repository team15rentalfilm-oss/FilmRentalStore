package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.film.FilmRequestDTO;
import com.iem.FilmRentalStore.dto.film.FilmResponseDTO;
import com.iem.FilmRentalStore.entity.Category;
import com.iem.FilmRentalStore.entity.Film;
import com.iem.FilmRentalStore.entity.Language;
import com.iem.FilmRentalStore.exception.ResourceNotFoundException;
import com.iem.FilmRentalStore.mapper.CategoryMapper;
import com.iem.FilmRentalStore.mapper.LanguageMapper;
import com.iem.FilmRentalStore.repository.CategoryRepository;
import com.iem.FilmRentalStore.repository.FilmRepository;
import com.iem.FilmRentalStore.repository.LanguageRepository;
import com.iem.FilmRentalStore.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final CategoryRepository categoryRepository;
    private final LanguageRepository languageRepository;

    // 🔥 CREATE
    @Override
    public FilmResponseDTO createFilm(FilmRequestDTO request) {

        Film film = new Film();

        film.setTitle(request.getTitle());
        film.setDescription(request.getDescription());
        film.setReleaseYear(request.getReleaseYear());
        film.setRentalDuration(request.getRentalDuration());
        film.setRentalRate(request.getRentalRate());
        film.setLength(request.getLength());
        film.setReplacementCost(request.getReplacementCost());
        film.setRating(request.getRating());
        film.setSpecialFeatures(request.getSpecialFeatures());

        // 🔥 Language
        Language language = languageRepository.findById(request.getLanguageId())
                .orElseThrow(() -> new ResourceNotFoundException("Language", "id", request.getLanguageId()));
        film.setLanguage(language);

        // 🔥 Categories
        Set<Category> categories = request.getCategoryIds().stream()
                .map(id -> categoryRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id)))
                .collect(Collectors.toSet());

        film.setCategories(categories);

        Film saved = filmRepository.save(film);

        return mapToResponse(saved);
    }

    // 🔥 GET BY ID
    @Override
    @Transactional(readOnly = true)
    public FilmResponseDTO getFilmById(Short id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));

        return mapToResponse(film);
    }

    // 🔥 GET ALL
    @Override
    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getAllFilms() {
        return filmRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // 🔥 UPDATE (FULL)
    @Override
    public FilmResponseDTO updateFilm(Short id, FilmRequestDTO request) {

        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));

        film.setTitle(request.getTitle());
        film.setDescription(request.getDescription());
        film.setReleaseYear(request.getReleaseYear());
        film.setRentalDuration(request.getRentalDuration());
        film.setRentalRate(request.getRentalRate());
        film.setLength(request.getLength());
        film.setReplacementCost(request.getReplacementCost());
        film.setRating(request.getRating());
        film.setSpecialFeatures(request.getSpecialFeatures());

        Language language = languageRepository.findById(request.getLanguageId())
                .orElseThrow(() -> new ResourceNotFoundException("Language", "id", request.getLanguageId()));

        film.setLanguage(language);

        Set<Category> categories = request.getCategoryIds().stream()
                .map(categoryId -> categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId)))
                .collect(Collectors.toSet());

        film.setCategories(categories);

        Film updated = filmRepository.save(film);

        return mapToResponse(updated);
    }

    // 🔥 PATCH (PARTIAL UPDATE)
    @Override
    public FilmResponseDTO patchFilm(Short id, FilmRequestDTO request) {

        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));

        if (request.getTitle() != null) film.setTitle(request.getTitle());
        if (request.getDescription() != null) film.setDescription(request.getDescription());
        if (request.getRating() != null) film.setRating(request.getRating());

        Film updated = filmRepository.save(film);

        return mapToResponse(updated);
    }

    // 🔥 DELETE
    @Override
    public void deleteFilm(Short id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));

        filmRepository.delete(film);
    }

    // 🔥 SEARCH
    @Override
    @Transactional(readOnly = true)
    public List<FilmResponseDTO> searchFilms(String title, Integer year) {

        return filmRepository.findAll()
                .stream()
                .filter(f -> title == null || f.getTitle().toLowerCase().contains(title.toLowerCase()))
                .filter(f -> year == null || f.getReleaseYear().equals(year))
                .map(this::mapToResponse)
                .toList();
    }

    // 🔥 MAPPER METHOD (IMPORTANT)
    private FilmResponseDTO mapToResponse(Film film) {

        FilmResponseDTO dto = new FilmResponseDTO();

        dto.setFilmId(film.getFilmId());
        dto.setTitle(film.getTitle());
        dto.setDescription(film.getDescription());
        dto.setReleaseYear(film.getReleaseYear());
        dto.setRating(film.getRating());
        dto.setLastUpdate(film.getLastUpdate());

        dto.setLanguage(LanguageMapper.toDTO(film.getLanguage()));

        dto.setCategories(
                film.getCategories().stream()
                        .map(CategoryMapper::toDTO)
                        .toList()
        );

        return dto;
    }
}