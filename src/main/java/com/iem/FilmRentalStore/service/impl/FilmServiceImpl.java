package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.FilmDTO;
import com.iem.FilmRentalStore.entity.Category;
import com.iem.FilmRentalStore.entity.Film;
import com.iem.FilmRentalStore.exception.ResourceNotFoundException;
import com.iem.FilmRentalStore.repository.CategoryRepository;
import com.iem.FilmRentalStore.repository.FilmRepository;
import com.iem.FilmRentalStore.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public FilmDTO createFilm(FilmDTO filmDTO) {
        Film film = new Film();
        film.setTitle(filmDTO.getTitle());
        film.setDescription(filmDTO.getDescription());

        // Link categories
        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(filmDTO.getCategoryIds()));
        film.setCategories(categories);

        Film savedFilm = filmRepository.save(film);

        // Map back to DTO
        return new FilmDTO(savedFilm.getFilmId(), savedFilm.getTitle(),
                savedFilm.getDescription(),
                savedFilm.getCategories().stream().map(Category::getId).collect(Collectors.toSet()));
    }

    @Override
    @Transactional(readOnly = true)
    public FilmDTO getFilmById(Short id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));

        return new FilmDTO(film.getFilmId(), film.getTitle(),
                film.getDescription(),
                film.getCategories().stream().map(Category::getId).collect(Collectors.toSet()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilmDTO> getAllFilms() {
        return filmRepository.findAll().stream().map(film ->
                new FilmDTO(film.getFilmId(), film.getTitle(), film.getDescription(),
                        film.getCategories().stream().map(Category::getId).collect(Collectors.toSet()))
        ).collect(Collectors.toList());
    }

    @Override
    public FilmDTO updateFilm(Short id, FilmDTO filmDTO) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));

        film.setTitle(filmDTO.getTitle());
        film.setDescription(filmDTO.getDescription());

        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(filmDTO.getCategoryIds()));
        film.setCategories(categories);

        Film updatedFilm = filmRepository.save(film);

        return new FilmDTO(updatedFilm.getFilmId(), updatedFilm.getTitle(), updatedFilm.getDescription(),
                updatedFilm.getCategories().stream().map(Category::getId).collect(Collectors.toSet()));
    }

    @Override
    public void deleteFilm(Short id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));
        filmRepository.delete(film);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilmDTO> searchFilms(String title, Integer year) {

        List<Film> films;

        if (title != null && !title.isEmpty()) {
            films = filmRepository.findAll().stream()
                    .filter(f -> f.getTitle() != null &&
                            f.getTitle().toLowerCase().contains(title.toLowerCase()))
                    .collect(Collectors.toList());
        } else {
            films = filmRepository.findAll();
        }

        return films.stream().map(film ->
                new FilmDTO(film.getFilmId(), film.getTitle(), film.getDescription(),
                        film.getCategories().stream()
                                .map(Category::getId)
                                .collect(Collectors.toSet()))
        ).collect(Collectors.toList());
    }
    @Override
    public FilmDTO patchFilm(Short id, FilmDTO filmDTO) {

        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));

        // Update only non-null fields
        if (filmDTO.getTitle() != null) {
            film.setTitle(filmDTO.getTitle());
        }

        if (filmDTO.getDescription() != null) {
            film.setDescription(filmDTO.getDescription());
        }

        if (filmDTO.getCategoryIds() != null) {
            Set<Category> categories = new HashSet<>(
                    categoryRepository.findAllById(filmDTO.getCategoryIds())
            );
            film.setCategories(categories);
        }

        Film updatedFilm = filmRepository.save(film);

        return new FilmDTO(updatedFilm.getFilmId(), updatedFilm.getTitle(),
                updatedFilm.getDescription(),
                updatedFilm.getCategories().stream()
                        .map(Category::getId)
                        .collect(Collectors.toSet()));
    }
}
