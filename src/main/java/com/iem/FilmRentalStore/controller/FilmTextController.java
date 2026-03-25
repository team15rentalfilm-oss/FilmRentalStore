package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.entity.FilmText;
import com.iem.FilmRentalStore.service.FilmTextService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/film-texts")
public class FilmTextController {

    private final FilmTextService filmTextService;

    public FilmTextController(FilmTextService filmTextService) {
        this.filmTextService = filmTextService;
    }

    @GetMapping
    public ResponseEntity<List<FilmText>> getFilmTexts(@RequestParam Map<String, String> searchParams) {
        if (searchParams.isEmpty()) {
            return ResponseEntity.ok(filmTextService.getAllFilmTexts());
        }
        return ResponseEntity.ok(filmTextService.getFilmTextsByFields(searchParams));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmText> getFilmTextById(@PathVariable Integer id) {
        return ResponseEntity.ok(filmTextService.getFilmTextById(id));
    }

    @PostMapping
    public ResponseEntity<FilmText> createFilmText(@RequestBody FilmText filmText) {
        return new ResponseEntity<>(filmTextService.createFilmText(filmText), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmText> updateFilmText(@PathVariable Integer id, @RequestBody FilmText filmText) {
        return ResponseEntity.ok(filmTextService.updateFilmText(id, filmText));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FilmText> patchFilmText(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(filmTextService.patchFilmText(id, updates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilmText(@PathVariable Integer id) {
        filmTextService.deleteFilmText(id);
        return ResponseEntity.noContent().build();
    }
}
