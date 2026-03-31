package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.film.FilmDTO;
import com.iem.FilmRentalStore.dto.film.FilmPatchDTO;
import com.iem.FilmRentalStore.dto.film.FilmRequestDTO;
import com.iem.FilmRentalStore.dto.film.FilmResponseDTO;
import com.iem.FilmRentalStore.service.FilmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/films")
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @PostMapping
    public FilmResponseDTO createFilm(@Valid @RequestBody FilmRequestDTO request) {
        return filmService.createFilm(request);
    }

    @GetMapping("/{id}")
    public FilmResponseDTO getFilmById(@PathVariable Short id) {
        return filmService.getFilmById(id);
    }

    @GetMapping
    public Page<FilmResponseDTO> getAllFilms( @PageableDefault(size = 10) Pageable pageable){

        return filmService.getAllFilms(pageable);
    }

    @PutMapping("/{id}")
    public FilmResponseDTO updateFilm(@PathVariable Short id,
                                      @Valid @RequestBody FilmRequestDTO request) {
        return filmService.updateFilm(id, request);
    }

    @PatchMapping("/{id}")
    public FilmResponseDTO patchFilm(@PathVariable Short id,
                                     @RequestBody FilmPatchDTO request) {
        return filmService.patchFilm(id, request);
    }



    @GetMapping("/search")
    public Page<FilmResponseDTO> searchFilms(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer releaseYear,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String actor,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Integer effectiveYear = year != null ? year : releaseYear;
        return filmService.searchFilms(title, effectiveYear, category, actor, pageable);
    }

    @GetMapping("/suggestions")
    public List<FilmDTO> suggestFilms(@RequestParam String title) {
        return filmService.suggestFilms(title);
    }
}
