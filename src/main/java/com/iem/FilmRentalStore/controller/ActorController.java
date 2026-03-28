package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.actor.ActorDTO;
import com.iem.FilmRentalStore.dto.actor.ActorRequestDTO;
import com.iem.FilmRentalStore.dto.actor.ActorResponseDTO;
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

    // 🔥 CREATE ACTOR
    @PostMapping
    public ActorResponseDTO createActor(@Valid @RequestBody ActorRequestDTO request) {
        return actorService.createActor(request);
    }

    // 🔥 GET ACTOR BY ID
    @GetMapping("/{id}")
    public ActorResponseDTO getActorById(@PathVariable Integer id) {
        return actorService.getActorById(id);
    }

    // 🔥 GET ALL ACTORS
    @GetMapping
    public List<ActorResponseDTO> getAllActors() {
        return actorService.getAllActors();
    }

    // 🔥 SEARCH ACTOR (PARTIAL MATCH - DB LEVEL)
    @GetMapping("/search")
    public List<ActorResponseDTO> searchActors(@RequestParam String name) {
        return actorService.searchActors(name);
    }

    // 🔥 UPDATE ACTOR
    @PutMapping("/{id}")
    public ActorResponseDTO updateActor(@PathVariable Integer id,
                                @Valid @RequestBody ActorRequestDTO request) {
        return actorService.updateActor(id, request);
    }
}