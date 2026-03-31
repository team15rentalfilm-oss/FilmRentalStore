package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.language.LanguageDTO;
import com.iem.FilmRentalStore.dto.language.LanguageRequestDTO;
import com.iem.FilmRentalStore.dto.language.LanguageResponseDTO;
import com.iem.FilmRentalStore.entity.Language;
import com.iem.FilmRentalStore.mapper.LanguageMapper;
import com.iem.FilmRentalStore.repository.LanguageRepository;
import com.iem.FilmRentalStore.service.LanguageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;

    @Override
    public LanguageResponseDTO createLanguage(LanguageRequestDTO request) {

        languageRepository.findByNameIgnoreCase(request.getName())
                .ifPresent(l -> {
                    throw new RuntimeException("Language already exists with name: " + request.getName());
                });

        Language language = LanguageMapper.toEntity(request);
        Language saved = languageRepository.save(language);

        return LanguageMapper.toResponseDTO(saved);
    }

    @Override
    public LanguageResponseDTO getLanguageById(Integer id) {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Language not found with id: " + id));

        return languageMapper.toResponseDTO(language);
    }

    @Override
    public LanguageResponseDTO getLanguageByName(String name) {

        Language language = languageRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new EntityNotFoundException("Language not found with name: " + name));

        return LanguageMapper.toResponseDTO(language);
    }

    @Override
    public List<LanguageResponseDTO> getAllLanguages() {
        return languageRepository.findAll()
                .stream()
                .map(LanguageMapper::toResponseDTO)
                .toList();
    }

    @Override
    public LanguageResponseDTO updateLanguage(Integer id, LanguageRequestDTO request) {

        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Language not found with id: " + id));

        languageRepository.findByNameIgnoreCase(request.getName())
                .ifPresent(existing -> {
                    if (!existing.getLanguageId().equals(id)) {
                        throw new RuntimeException("Language already exists with name: " + request.getName());
                    }
                });

        language.setName(request.getName());

        Language updated = languageRepository.save(language);
        return LanguageMapper.toResponseDTO(updated);
    }

    private String normalize(String value) {
        return value == null ? null : value.trim().toLowerCase();
    }

    private Language getOrCreateLanguage(String name) {

        String normalized = normalize(name);

        return languageRepository.findByNameIgnoreCase(normalized)
                .orElseGet(() -> {
                    try {
                        Language lang = new Language();
                        lang.setName(normalized);
                        return languageRepository.save(lang);
                    } catch (Exception e) {
                        try {
                            return languageRepository.findByNameIgnoreCase(normalized)
                                    .orElseThrow(() -> e);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
    }


}
