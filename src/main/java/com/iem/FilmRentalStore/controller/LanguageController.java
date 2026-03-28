package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.language.LanguageDTO;
import com.iem.FilmRentalStore.dto.language.LanguageRequestDTO;
import com.iem.FilmRentalStore.dto.language.LanguageResponseDTO;
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

    // ✅ Create (No duplicate)
    @PostMapping
    public LanguageResponseDTO createLanguage(@Valid @RequestBody LanguageRequestDTO request) {
        return languageService.createLanguage(request);
    }

    // ✅ Get by ID
    @GetMapping("/{id}")
    public LanguageResponseDTO getLanguageById(@PathVariable Integer id) {
        return languageService.getLanguageById(id);
    }

    // ✅ Get all
    @GetMapping
    public List<LanguageResponseDTO> getAllLanguages() {
        return languageService.getAllLanguages();
    }

    // ✅ Get by name
    @GetMapping("/search")
    public LanguageResponseDTO getLanguageByName(@RequestParam String name) {
        return languageService.getLanguageByName(name);
    }

    // ✅ Update (No duplicate)
    @PutMapping("/{id}")
    public LanguageResponseDTO updateLanguage(@PathVariable Integer id,
                                      @Valid @RequestBody LanguageRequestDTO request) {
        return languageService.updateLanguage(id, request);
    }
}