package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.FilmCategoryDTO;
import com.iem.FilmRentalStore.entity.FilmCategory;
import com.iem.FilmRentalStore.entity.FilmCategoryId;
import com.iem.FilmRentalStore.repository.FilmCategoryRepository;
import com.iem.FilmRentalStore.service.FilmCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmCategoryServiceImpl implements FilmCategoryService {

    private final FilmCategoryRepository repo;

    @Override
    public List<FilmCategoryDTO> getAll() {
        return repo.findAll().stream()
                .map(fc -> new FilmCategoryDTO(
                        fc.getId().getFilmId(),
                        fc.getId().getCategoryId()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public FilmCategoryDTO create(FilmCategoryDTO dto) {

        FilmCategory fc = new FilmCategory();

        FilmCategoryId id = new FilmCategoryId(
                dto.filmId,
                dto.categoryId
        );

        fc.setId(id);

        repo.save(fc);

        return dto;
    }

    @Override
    public void delete(Short filmId, Byte categoryId) {

        FilmCategoryId id = new FilmCategoryId(filmId, categoryId);
        repo.deleteById(id);
    }
}
