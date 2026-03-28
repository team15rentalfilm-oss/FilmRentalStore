package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.language.LanguageDTO;
import com.iem.FilmRentalStore.dto.language.LanguageRequestDTO;
import com.iem.FilmRentalStore.service.LanguageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @PostMapping
    public LanguageDTO createLanguage(@Valid @RequestBody LanguageRequestDTO request) {
        return languageService.createLanguage(request);
    }

    @GetMapping("/{id}")
    public LanguageDTO getLanguageById(@PathVariable Integer id) {
        return languageService.getLanguageById(id);
    }

    @GetMapping
    public List<LanguageDTO> getAllLanguages() {
        return languageService.getAllLanguages();
    }

    @PutMapping("/{id}")
    public LanguageDTO updateLanguage(@PathVariable Integer id,
                                      @Valid @RequestBody LanguageRequestDTO request) {
        return languageService.updateLanguage(id, request);
    }
}