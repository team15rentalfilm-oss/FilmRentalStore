package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.LanguageDTO;
import com.iem.FilmRentalStore.entity.Language;
import com.iem.FilmRentalStore.repository.LanguageRepository;
import com.iem.FilmRentalStore.service.LanguageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository repo;

    public LanguageServiceImpl(LanguageRepository repo) {
        this.repo = repo;
    }

    private LanguageDTO mapToDTO(Language l) {
        LanguageDTO dto = new LanguageDTO();
        dto.setLanguageId(l.getLanguageId());
        dto.setName(l.getName());
        return dto;
    }

    private Language mapToEntity(LanguageDTO dto) {
        Language l = new Language();
        l.setLanguageId(dto.getLanguageId());
        l.setName(dto.getName());
        return l;
    }

    public List<LanguageDTO> getAll() {
        return repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public LanguageDTO getById(int id) {
        return mapToDTO(repo.findById(id).orElseThrow());
    }

    public List<LanguageDTO> getByName(String name) {
        return repo.findByName(name).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public LanguageDTO create(LanguageDTO dto) {
        return mapToDTO(repo.save(mapToEntity(dto)));
    }

    public LanguageDTO update(int id, LanguageDTO dto) {
        Language l = mapToEntity(dto);
        l.setLanguageId(id);
        return mapToDTO(repo.save(l));
    }

    public LanguageDTO patch(int id, LanguageDTO dto) {
        Language existing = repo.findById(id).orElseThrow();

        if (dto.getName() != null)
            existing.setName(dto.getName());

        return mapToDTO(repo.save(existing));
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
