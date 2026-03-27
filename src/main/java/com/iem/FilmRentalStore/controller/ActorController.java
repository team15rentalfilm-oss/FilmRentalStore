package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.ActorDTO;
import com.iem.FilmRentalStore.service.ActorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    private final ActorService service;

    public ActorController(ActorService service) {
        this.service = service;
    }

    // GET ALL
    @GetMapping
    public List<ActorDTO> getAll() {
        return service.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ActorDTO getById(@PathVariable Short id) {
        return service.getById(id);
    }

    // GET BY FIELD
    @GetMapping("/search")
    public List<ActorDTO> getByName(@RequestParam String name) {
        return service.getByFirstName(name);
    }

    // POST
    @PostMapping
    public ActorDTO create(@Valid @RequestBody ActorDTO dto) {
        return service.create(dto);
    }

    // PUT
    @PutMapping("/{id}")
    public ActorDTO update(@PathVariable Short id, @Valid @RequestBody ActorDTO dto) {
        return service.update(id, dto);
    }

    // PATCH
    @PatchMapping("/{id}")
    public ActorDTO patch(@PathVariable Short id, @RequestBody ActorDTO dto) {
        return service.patch(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Short id) {
        service.delete(id);
    }
}
