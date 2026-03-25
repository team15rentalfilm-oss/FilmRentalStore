package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.FilmActorDTO;
import com.iem.FilmRentalStore.service.FilmActorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film-actor")
public class FilmActorController {

    private final FilmActorService service;

    public FilmActorController(FilmActorService service) {
        this.service = service;
    }

    // GET ALL
    @GetMapping
    public List<FilmActorDTO> getAll() {
        return service.getAll();
    }

    // POST
    @PostMapping
    public FilmActorDTO create(@Valid @RequestBody FilmActorDTO dto) {
        return service.create(dto);
    }

    // DELETE
    @DeleteMapping
    public void delete(@RequestParam int actorId,
                       @RequestParam int filmId) {
        service.delete(actorId, filmId);
    }
}
