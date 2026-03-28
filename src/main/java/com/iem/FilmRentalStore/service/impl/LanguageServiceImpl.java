package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.language.LanguageDTO;
import com.iem.FilmRentalStore.dto.language.LanguageRequestDTO;
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
    public LanguageDTO createLanguage(LanguageRequestDTO request) {
        Language language = languageMapper.toEntity(request);
        Language saved = languageRepository.save(language);
        return languageMapper.toDTO(saved);
    }

    @Override
    public LanguageDTO getLanguageById(Integer id) {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Language not found with id: " + id));

        return languageMapper.toDTO(language);
    }

    @Override
    public List<LanguageDTO> getAllLanguages() {
        return languageRepository.findAll()
                .stream()
                .map(LanguageMapper::toDTO)
                .toList();
    }

    @Override
    public LanguageDTO updateLanguage(Integer id, LanguageRequestDTO request) {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Language not found with id: " + id));

        language.setName(request.getName());

        Language updated = languageRepository.save(language);
        return languageMapper.toDTO(updated);
    }

}