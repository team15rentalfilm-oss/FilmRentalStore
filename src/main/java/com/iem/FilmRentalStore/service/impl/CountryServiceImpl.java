package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.CountryDTO;
import com.iem.FilmRentalStore.entity.Country;
import com.iem.FilmRentalStore.repository.CountryRepository;
import com.iem.FilmRentalStore.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository repo;

    @Override
    public CountryDTO.Response create(CountryDTO.Request dto) {
        Country c = CountryDTO.toEntity(dto);
        return CountryDTO.toResponse(repo.save(c));
    }

    @Override
    public List<CountryDTO.Response> getAll() {
        return repo.findAll()
                .stream()
                .map(CountryDTO::toResponse)
                .toList();
    }

    @Override
    public CountryDTO.Response getById(Short id) {
        Country c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));
        return CountryDTO.toResponse(c);
    }

    @Override
    public CountryDTO.Response update(Short id, CountryDTO.Request dto) {
        Country c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));

        c.setCountry(dto.getCountry());

        return CountryDTO.toResponse(repo.save(c));
    }

    @Override
    public void delete(Short id) {
        repo.deleteById(id);
    }
}