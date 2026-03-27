package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.entity.FilmText;
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

    public FilmTextServiceImpl(FilmTextRepository filmTextRepository) {
        this.filmTextRepository = filmTextRepository;
    }

    @Override
    public List<FilmText> getAllFilmTexts() {
        return filmTextRepository.findAll();
    }

    @Override
    public List<FilmText> getFilmTextsByFields(Map<String, String> searchParams) {
        Specification<FilmText> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            searchParams.forEach((key, value) -> {
                try {
                    root.get(key);
                    predicates.add(cb.equal(root.get(key).as(String.class), value));
                } catch (IllegalArgumentException ignored) {
                }
            });
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return filmTextRepository.findAll(spec);
    }

    @Override
    public FilmText getFilmTextById(Integer id) {
        return filmTextRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FilmText not found with id: " + id));
    }

    @Override
    public FilmText createFilmText(FilmText filmText) {
        return filmTextRepository.save(filmText);
    }

    @Override
    public FilmText updateFilmText(Integer id, FilmText filmTextDetails) {
        FilmText filmText = getFilmTextById(id);
        filmText.setTitle(filmTextDetails.getTitle());
        filmText.setDescription(filmTextDetails.getDescription());
        return filmTextRepository.save(filmText);
    }

    @Override
    public FilmText patchFilmText(Integer id, Map<String, Object> updates) {
        FilmText filmText = getFilmTextById(id);

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(FilmText.class, key);
            if (field != null && !key.equals("filmId")) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, filmText, value);
            }
        });

        return filmTextRepository.save(filmText);
    }

    @Override
    public void deleteFilmText(Integer id) {
        FilmText filmText = getFilmTextById(id);
        filmTextRepository.delete(filmText);
    }
}
