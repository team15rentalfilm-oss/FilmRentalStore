package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.FilmCategoryDTO;
import com.iem.FilmRentalStore.service.FilmCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/film-category")
@RequiredArgsConstructor
public class FilmCategoryController {

    private final FilmCategoryService service;

    @GetMapping
    public List<FilmCategoryDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    public FilmCategoryDTO create(@RequestBody FilmCategoryDTO dto) {
        return service.create(dto);
    }

    @DeleteMapping
    public void delete(@RequestParam Short filmId,
                       @RequestParam Byte categoryId) {
        service.delete(filmId, categoryId);
    }
}
