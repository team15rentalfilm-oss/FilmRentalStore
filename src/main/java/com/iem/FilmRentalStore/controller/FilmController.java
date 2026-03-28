package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.film.FilmRequestDTO;
import com.iem.FilmRentalStore.dto.film.FilmResponseDTO;
import com.iem.FilmRentalStore.service.FilmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/films")
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    // 🔥 CREATE
    @PostMapping
    public FilmResponseDTO createFilm(@Valid @RequestBody FilmRequestDTO request) {
        return filmService.createFilm(request);
    }

    // 🔥 GET BY ID
    @GetMapping("/{id}")
    public FilmResponseDTO getFilmById(@PathVariable Short id) {
        return filmService.getFilmById(id);
    }

    // 🔥 GET ALL
    @GetMapping
    public List<FilmResponseDTO> getAllFilms() {
        return filmService.getAllFilms();
    }

    // 🔥 UPDATE (FULL)
    @PutMapping("/{id}")
    public FilmResponseDTO updateFilm(@PathVariable Short id,
                                      @Valid @RequestBody FilmRequestDTO request) {
        return filmService.updateFilm(id, request);
    }

    // 🔥 PATCH (PARTIAL)
    @PatchMapping("/{id}")
    public FilmResponseDTO patchFilm(@PathVariable Short id,
                                     @RequestBody FilmRequestDTO request) {
        return filmService.patchFilm(id, request);
    }

    // 🔥 DELETE
    @DeleteMapping("/{id}")
    public void deleteFilm(@PathVariable Short id) {
        filmService.deleteFilm(id);
    }

    // 🔥 SEARCH
    @GetMapping("/search")
    public List<FilmResponseDTO> searchFilms(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer year) {

        return filmService.searchFilms(title, year);
    }
}