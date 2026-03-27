package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.FilmTextDTO;
import com.iem.FilmRentalStore.entity.Film;
import com.iem.FilmRentalStore.entity.FilmText;
import com.iem.FilmRentalStore.repository.FilmRepository;
import com.iem.FilmRentalStore.repository.FilmTextRepository;
import com.iem.FilmRentalStore.service.FilmTextService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FilmTextServiceImpl implements FilmTextService {

    private final FilmTextRepository filmTextRepository;
    private final FilmRepository filmRepository;

    public FilmTextServiceImpl(FilmTextRepository filmTextRepository, FilmRepository filmRepository) {
        this.filmTextRepository = filmTextRepository;
        this.filmRepository = filmRepository;
    }

    private FilmTextDTO toDto(FilmText filmText) {
        return new FilmTextDTO(
                filmText.getFilmId(),
                filmText.getTitle(),
                filmText.getDescription()
        );
    }

    @Override
    public List<FilmTextDTO> getAllFilmTexts() {
        return filmTextRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public List<FilmTextDTO> getFilmTextsByFields(Map<String, String> searchParams) {
        Specification<FilmText> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            searchParams.forEach((key, value) -> {
                try {
                    if ("filmId".equals(key)) {
                        predicates.add(cb.equal(root.get("filmId"), Short.valueOf(value)));
                    } else if ("title".equals(key) || "description".equals(key)) {
                        predicates.add(cb.equal(root.get(key), value));
                    }
                } catch (Exception ignored) {
                }
            });

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return filmTextRepository.findAll(spec).stream().map(this::toDto).toList();
    }

    @Override
    public FilmTextDTO getFilmTextById(Short id) {
        FilmText filmText = filmTextRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FilmText not found with id: " + id));
        return toDto(filmText);
    }

    @Override
    public FilmTextDTO createFilmText(FilmTextDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Request body cannot be null");
        }
        if (dto.filmId() == null) {
            throw new IllegalArgumentException("filmId is required");
        }
        if (dto.title() == null || dto.title().isBlank()) {
            throw new IllegalArgumentException("title is required");
        }

        Film film = filmRepository.findById(dto.filmId())
                .orElseThrow(() -> new EntityNotFoundException("Film not found with id: " + dto.filmId()));

        if (filmTextRepository.existsById(dto.filmId())) {
            throw new IllegalArgumentException("FilmText already exists for film id: " + dto.filmId());
        }

        FilmText filmText = new FilmText();
        filmText.setFilm(film);
        filmText.setTitle(dto.title());
        filmText.setDescription(dto.description());

        return toDto(filmTextRepository.save(filmText));
    }

    @Override
    public FilmTextDTO updateFilmText(Short id, FilmTextDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Request body cannot be null");
        }

        FilmText filmText = filmTextRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FilmText not found with id: " + id));

        filmText.setTitle(dto.title());
        filmText.setDescription(dto.description());

        return toDto(filmTextRepository.save(filmText));
    }

    @Override
    public FilmTextDTO patchFilmText(Short id, Map<String, Object> updates) {
        FilmText filmText = filmTextRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FilmText not found with id: " + id));

        updates.forEach((key, value) -> {
            if ("filmId".equals(key) || "film".equals(key)) {
                return;
            }

            Field field = ReflectionUtils.findField(FilmText.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, filmText, value);
            }
        });

        return toDto(filmTextRepository.save(filmText));
    }

    @Override
    public void deleteFilmText(Short id) {
        FilmText filmText = filmTextRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FilmText not found with id: " + id));
        filmTextRepository.delete(filmText);
    }
}