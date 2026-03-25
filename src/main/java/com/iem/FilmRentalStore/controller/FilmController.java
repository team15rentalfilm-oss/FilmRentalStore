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

    // Get film by ID
    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> getFilm(@PathVariable Short id) {
        return ResponseEntity.ok(filmService.getFilmById(id));
    }

    // Update film
    @PutMapping("/{id}")
    public ResponseEntity<FilmDTO> updateFilm(@PathVariable Short id,
                                              @Valid @RequestBody FilmDTO filmDTO) {
        return ResponseEntity.ok(filmService.updateFilm(id, filmDTO));
    }

    // Delete film
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Short id) {
        filmService.deleteFilm(id);
        return ResponseEntity.noContent().build();
    }
}
