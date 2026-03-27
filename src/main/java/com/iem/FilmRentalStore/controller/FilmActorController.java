package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.FilmActorDTO;
import com.iem.FilmRentalStore.dto.FilmActorDetailsDTO;
import com.iem.FilmRentalStore.service.FilmActorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/film-actor")
public class FilmActorController {

    private final FilmActorService service;

    public FilmActorController(FilmActorService service) {
        this.service = service;
    }

    @GetMapping
    public List<FilmActorDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/details")
    public List<FilmActorDetailsDTO> getDetails() {
        return service.getFilmActorDetails();
    }

    @PostMapping
    public FilmActorDTO create(@Valid @RequestBody FilmActorDTO dto) {
        return service.create(dto);
    }

    @DeleteMapping("/{actorId}/{filmId}")
    public void delete(@PathVariable Short actorId,
                       @PathVariable Short filmId) {
        service.delete(actorId, filmId);
    }
}