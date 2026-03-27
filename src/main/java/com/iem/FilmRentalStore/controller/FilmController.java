package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.FilmDTO;
import com.iem.FilmRentalStore.service.FilmService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping
    public ResponseEntity<FilmDTO> createFilm(@Valid @RequestBody FilmDTO filmDTO) {
        FilmDTO created = filmService.createFilm(filmDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<FilmDTO>> getAllFilms() {
        return ResponseEntity.ok(filmService.getAllFilms());
    }

    @GetMapping("/search")
    public ResponseEntity<List<FilmDTO>> searchFilms(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer year) {
        return ResponseEntity.ok(filmService.searchFilms(title, year));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> getFilm(@PathVariable Short id) {
        return ResponseEntity.ok(filmService.getFilmById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmDTO> updateFilm(@PathVariable Short id,
                                              @Valid @RequestBody FilmDTO filmDTO) {
        return ResponseEntity.ok(filmService.updateFilm(id, filmDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FilmDTO> patchFilm(@PathVariable Short id,
                                             @RequestBody FilmDTO filmDTO) {
        return ResponseEntity.ok(filmService.patchFilm(id, filmDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Short id) {
        filmService.deleteFilm(id);
        return ResponseEntity.noContent().build();
    }
}