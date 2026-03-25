package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.FilmDTO;
import com.iem.FilmRentalStore.service.FilmService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    // Create a new film
    @PostMapping
    public ResponseEntity<FilmDTO> createFilm(@Valid @RequestBody FilmDTO filmDTO) {
        FilmDTO created = filmService.createFilm(filmDTO);
        return ResponseEntity.ok(created);
    }

    // Get all films
    @GetMapping
    public ResponseEntity<List<FilmDTO>> getAllFilms() {
        return ResponseEntity.ok(filmService.getAllFilms());
    }

    // 🔍 SEARCH FILMS (ADDED)
    @GetMapping("/search")
    public ResponseEntity<List<FilmDTO>> searchFilms(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer year) {
        return ResponseEntity.ok(filmService.searchFilms(title, year));
    }

    // Get film by ID (FIXED to avoid conflict)
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<FilmDTO> getFilm(@PathVariable Short id) {
        return ResponseEntity.ok(filmService.getFilmById(id));
    }

    // Update film (full update)
    @PutMapping("/{id}")
    public ResponseEntity<FilmDTO> updateFilm(@PathVariable Short id,
                                              @Valid @RequestBody FilmDTO filmDTO) {
        return ResponseEntity.ok(filmService.updateFilm(id, filmDTO));
    }

    // 🧩 PATCH (PARTIAL UPDATE) (ADDED)
    @PatchMapping("/{id}")
    public ResponseEntity<FilmDTO> patchFilm(
            @PathVariable Short id,
            @RequestBody FilmDTO filmDTO) {
        return ResponseEntity.ok(filmService.patchFilm(id, filmDTO));
    }

    // Delete film
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Short id) {
        filmService.deleteFilm(id);
        return ResponseEntity.noContent().build();
    }
}