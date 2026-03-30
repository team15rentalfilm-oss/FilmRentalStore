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
    private final ActorRepository actorRepository;

    // 🔥 CREATE
    @Override
    public FilmResponseDTO createFilm(FilmRequestDTO request) {

        Language language = getOrCreateLanguage(request.getLanguage());
        Set<Category> categories = getOrCreateCategories(request.getCategories());
        Set<Actor> actors = getOrCreateActors(request.getActors());

        Film film = FilmMapper.toEntity(request, language, categories, actors);

        return FilmMapper.toResponseDTO(filmRepository.save(film));
    }

    // 🔥 GET BY ID
    @Override
    @Transactional(readOnly = true)
    public FilmResponseDTO getFilmById(Short id) {

        Film film = filmRepository.findWithRelationsByFilmId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));

        return FilmMapper.toResponseDTO(film);
    }

    // 🔥 GET ALL (PAGINATION)
    @Override
    @Transactional(readOnly = true)
    public Page<FilmResponseDTO> getAllFilms(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return filmRepository.findAll(pageable)
                .map(film -> {
                    // 🔥 Force initialize lazy fields
                    film.getSpecialFeatures().size();
                    film.getCategories().size();
                    film.getActors().size();

                    return FilmMapper.toResponseDTO(film);
                });
    }

    // 🔥 UPDATE (FULL)
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
    // 🔥 PATCH
    @Override
    public FilmResponseDTO patchFilm(Short id, FilmPatchDTO request) {

        Film film = filmRepository.findWithRelationsByFilmId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));

        FilmMapper.patchEntity(film, request);

        Film updated = filmRepository.save(film);

        return FilmMapper.toResponseDTO(updated);
    }

    // 🔥 SEARCH (DB LEVEL)
    @Override
    @Transactional(readOnly = true)
    public Page<FilmResponseDTO> searchFilms(
            String title,
            Integer year,
            String category,
            String actor,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        // 🔥 Already uses EntityGraph → no lazy issue
        Page<Film> pageData = filmRepository.findAll(pageable);

        List<Film> filtered = pageData.getContent().stream()

                // Title filter
                .filter(f -> title == null ||
                        f.getTitle().toLowerCase().contains(title.toLowerCase()))

                // Year filter
                .filter(f -> year == null ||
                        year.equals(f.getReleaseYear()))

                // Category filter
                .filter(f -> category == null ||
                        f.getCategories().stream()
                                .anyMatch(c -> c.getName().equalsIgnoreCase(category)))

                // Actor filter
                .filter(f -> actor == null ||
                        f.getActors().stream()
                                .anyMatch(a ->
                                        (a.getFirstName() + " " + a.getLastName())
                                                .equalsIgnoreCase(actor)))

                .toList();

        List<FilmResponseDTO> content = filtered.stream()
                .map(FilmMapper::toResponseDTO)
                .toList();

        return new org.springframework.data.domain.PageImpl<>(
                content,
                pageable,
                filtered.size()
        );
    }

    // 🔥 HELPER METHODS (VERY IMPORTANT)

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
