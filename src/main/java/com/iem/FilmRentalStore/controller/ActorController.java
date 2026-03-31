package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.actor.ActorDTO;
import com.iem.FilmRentalStore.dto.actor.ActorRequestDTO;
import com.iem.FilmRentalStore.dto.actor.ActorResponseDTO;
import com.iem.FilmRentalStore.service.ActorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @PostMapping
    public ActorResponseDTO createActor(@Valid @RequestBody ActorRequestDTO request) {
        return actorService.createActor(request);
    }

    @GetMapping("/{id}")
    public ActorResponseDTO getActorById(@PathVariable Short id) {
        return actorService.getActorById(id);
    }

    // ✅ PAGINATED GET ALL
    @GetMapping
    public Page<ActorResponseDTO> getAllActors(
            @PageableDefault(size = 10, sort = "firstName") Pageable pageable) {
        return actorService.getAllActors(pageable);
    }

    // ✅ PAGINATED SEARCH
    @GetMapping("/search")
    public Page<ActorResponseDTO> searchActors(
            @RequestParam String name,
            @PageableDefault(size = 10, sort = "firstName") Pageable pageable) {
        return actorService.searchActors(name, pageable);
    }

    @PutMapping("/{id}")
    public ActorResponseDTO updateActor(@PathVariable Short id,
                                        @Valid @RequestBody ActorRequestDTO request) {
        return actorService.updateActor(id, request);
    }
}