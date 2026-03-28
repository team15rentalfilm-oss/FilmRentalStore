package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.actor.ActorDTO;
import com.iem.FilmRentalStore.dto.actor.ActorRequestDTO;
import com.iem.FilmRentalStore.service.ActorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    // 🔥 CREATE
    @PostMapping
    public ActorDTO createActor(@Valid @RequestBody ActorRequestDTO request) {
        return actorService.createActor(request);
    }

    // 🔥 GET BY ID
    @GetMapping("/{id}")
    public ActorDTO getActorById(@PathVariable Integer id) {
        return actorService.getActorById(id);
    }

    // 🔥 GET ALL
    @GetMapping
    public List<ActorDTO> getAllActors() {
        return actorService.getAllActors();
    }

    // 🔥 UPDATE
    @PutMapping("/{id}")
    public ActorDTO updateActor(@PathVariable Integer id,
                                @Valid @RequestBody ActorRequestDTO request) {
        return actorService.updateActor(id, request);
    }

    // 🔥 SEARCH
    @GetMapping("/search")
    public List<ActorDTO> searchActors(@RequestParam String name) {
        return actorService.getAllActors()
                .stream()
                .filter(a -> a.getFirstName().toLowerCase().contains(name.toLowerCase())
                        || a.getLastName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
}