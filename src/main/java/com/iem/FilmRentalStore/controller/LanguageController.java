package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.service.LanguageService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {

    private final LanguageService service;

    public LanguageController(LanguageService service) {
        this.service = service;
    }

    // GET ALL
    @GetMapping
    public List<LanguageDTO> getAll() {
        return service.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public LanguageDTO getById(@PathVariable int id) {
        return service.getById(id);
    }

    // GET BY FIELD
    @GetMapping("/search")
    public List<LanguageDTO> getByName(@RequestParam String name) {
        return service.getByName(name);
    }

    // POST
    @PostMapping
    public LanguageDTO create(@Valid @RequestBody LanguageDTO dto) {
        return service.create(dto);
    }

    // PUT
    @PutMapping("/{id}")
    public LanguageDTO update(@PathVariable int id, @Valid @RequestBody LanguageDTO dto) {
        return service.update(id, dto);
    }

    // PATCH
    @PatchMapping("/{id}")
    public LanguageDTO patch(@PathVariable int id, @RequestBody LanguageDTO dto) {
        return service.patch(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
