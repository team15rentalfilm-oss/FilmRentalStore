package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.film.FilmDTO;
import com.iem.FilmRentalStore.dto.film.FilmPatchDTO;
import com.iem.FilmRentalStore.dto.film.FilmRequestDTO;
import com.iem.FilmRentalStore.dto.film.FilmResponseDTO;
import com.iem.FilmRentalStore.entity.Actor;
import com.iem.FilmRentalStore.entity.Category;
import com.iem.FilmRentalStore.entity.Film;
import com.iem.FilmRentalStore.entity.Language;
import com.iem.FilmRentalStore.exception.ResourceNotFoundException;
import com.iem.FilmRentalStore.mapper.FilmMapper;
import com.iem.FilmRentalStore.repository.ActorRepository;
import com.iem.FilmRentalStore.repository.CategoryRepository;
import com.iem.FilmRentalStore.repository.FilmRepository;
import com.iem.FilmRentalStore.repository.LanguageRepository;
import com.iem.FilmRentalStore.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Hibernate;
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
    private final ActorRepository actorRepository;

    @Override
    public FilmResponseDTO createFilm(FilmRequestDTO request) {

        Language language = getOrCreateLanguage(request.getLanguage());
        Set<Category> categories = getOrCreateCategories(request.getCategories());
        Set<Actor> actors = getOrCreateActors(request.getActors());

        Film film = FilmMapper.toEntity(request, language, categories, actors);

        return FilmMapper.toResponseDTO(filmRepository.save(film));
    }

    @Override
    @Transactional(readOnly = true)
    public FilmResponseDTO getFilmById(Short id) {

        Film film = filmRepository.findWithRelationsByFilmId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));

        return FilmMapper.toResponseDTO(film);
    }


    private Pageable sanitizePageable(Pageable pageable) {

        int page = pageable.getPageNumber();
        int size = 10;

        List<String> allowed = List.of(
                "title",
                "releaseYear",
                "rentalRate"
        );

        Sort safeSort = Sort.unsorted();

        for (Sort.Order order : pageable.getSort()) {
            if (allowed.contains(order.getProperty())) {
                safeSort = safeSort.and(Sort.by(order));
            }
        }

        if (safeSort.isUnsorted()) {
            safeSort = Sort.by(Sort.Direction.ASC, "title");
        }

        return PageRequest.of(
                Math.max(page, 0),
                size,
                safeSort
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FilmResponseDTO> getAllFilms(Pageable pageable) {

        pageable = sanitizePageable(pageable);

        return filmRepository.findAll(pageable)
                .map(film -> {
                    Hibernate.initialize(film.getCategories());
                    Hibernate.initialize(film.getActors());
                    return FilmMapper.toResponseDTO(film);
                });
    }


    @Override
    public FilmResponseDTO updateFilm(Short id, FilmRequestDTO request) {

        Film film = filmRepository.findWithRelationsByFilmId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));

        Language language = getOrCreateLanguage(request.getLanguage());
        Set<Category> categories = getOrCreateCategories(request.getCategories());
        Set<Actor> actors = getOrCreateActors(request.getActors());

        FilmMapper.updateEntity(film, request, language, categories, actors);

        return FilmMapper.toResponseDTO(filmRepository.save(film));
    }

    @Override
    public FilmResponseDTO patchFilm(Short id, FilmPatchDTO request) {

        Film film = filmRepository.findWithRelationsByFilmId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));

        FilmMapper.patchEntity(film, request);

        if (request.getLanguage() != null) {
            film.setLanguage(getOrCreateLanguage(request.getLanguage()));
        }
        if (request.getCategories() != null) {
            film.setCategories(getOrCreateCategories(request.getCategories()));
        }
        if (request.getActors() != null) {
            film.setActors(getOrCreateActors(request.getActors()));
        }

        Film updated = filmRepository.save(film);

        return FilmMapper.toResponseDTO(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FilmResponseDTO> searchFilms(
            String title,
            Integer year,
            String category,
            String actor,
            Pageable pageable) {

        pageable = sanitizePageable(pageable);

        Page<Film> pageData = filmRepository.searchFilms(title, year, category, actor, pageable);

        return pageData
                .map(film -> {
                    Hibernate.initialize(film.getCategories());
                    Hibernate.initialize(film.getActors());
                    return FilmMapper.toResponseDTO(film);
                });
    }


    @Override
    @Transactional(readOnly = true)
    public List<FilmDTO> suggestFilms(String title) {
        String query = title == null ? "" : title.trim();
        return filmRepository.findTop10ByTitleContainingIgnoreCaseOrderByTitleAsc(query).stream()
                .map(FilmMapper::toDTO)
                .toList();
    }

    private Language getOrCreateLanguage(String name) {
        return languageRepository.findByNameIgnoreCase(name)
                .orElseGet(() -> {
                    Language lang = new Language();
                    lang.setName(name);
                    return languageRepository.save(lang);
                });
    }

    private Set<Category> getOrCreateCategories(Set<String> names) {
        return names.stream()
                .map(name -> categoryRepository.findByNameIgnoreCase(name)
                        .orElseGet(() -> {
                            Category cat = new Category();
                            cat.setName(name);
                            return categoryRepository.save(cat);
                        }))
                .collect(Collectors.toSet());
    }

    private Set<Actor> getOrCreateActors(Set<String> names) {
        return names.stream()
                .map(fullName -> {
                    String[] parts = fullName.split(" ", 2);
                    String firstName = parts[0];
                    String lastName = parts.length > 1 ? parts[1] : "";

                    return actorRepository
                            .findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName)
                            .orElseGet(() -> {
                                Actor actor = new Actor();
                                actor.setFirstName(firstName);
                                actor.setLastName(lastName);
                                return actorRepository.save(actor);
                            });
                })
                .collect(Collectors.toSet());
    }
}
