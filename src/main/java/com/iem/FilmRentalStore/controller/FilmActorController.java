package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.actor.ActorResponseDTO;
import com.iem.FilmRentalStore.dto.film.FilmResponseDTO;
import com.iem.FilmRentalStore.service.FilmActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/film-actor")
@RequiredArgsConstructor
public class FilmActorController {

    private final FilmActorService filmActorService;

    @GetMapping("/films-by-actor")
    public List<FilmResponseDTO> getFilmsByActor(@RequestParam String name) {
        return filmActorService.getFilmsByActor(name);
    }

    @GetMapping("/actors-by-film")
    public List<ActorResponseDTO> getActorsByFilm(@RequestParam String title) {
        return filmActorService.getActorsByFilm(title);
    }
}
