package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.FilmTextDTO;
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
    public ResponseEntity<List<FilmTextDTO>> getFilmTexts(@RequestParam Map<String, String> searchParams) {
        if (searchParams.isEmpty()) {
            return ResponseEntity.ok(filmTextService.getAllFilmTexts());
        }
        return ResponseEntity.ok(filmTextService.getFilmTextsByFields(searchParams));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmTextDTO> getFilmTextById(@PathVariable Short id) {
        return ResponseEntity.ok(filmTextService.getFilmTextById(id));
    }

    @PostMapping
    public ResponseEntity<FilmTextDTO> createFilmText(@RequestBody FilmTextDTO filmTextDto) {
        return new ResponseEntity<>(filmTextService.createFilmText(filmTextDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmTextDTO> updateFilmText(@PathVariable Short id, @RequestBody FilmTextDTO filmTextDto) {
        return ResponseEntity.ok(filmTextService.updateFilmText(id, filmTextDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FilmTextDTO> patchFilmText(@PathVariable Short id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(filmTextService.patchFilmText(id, updates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilmText(@PathVariable Short id) {
        filmTextService.deleteFilmText(id);
        return ResponseEntity.noContent().build();
    }
}