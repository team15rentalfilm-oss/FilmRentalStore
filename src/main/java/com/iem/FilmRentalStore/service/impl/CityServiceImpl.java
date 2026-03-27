package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.CityDTO;
import com.iem.FilmRentalStore.entity.City;
import com.iem.FilmRentalStore.entity.Country;
import com.iem.FilmRentalStore.exception.ResourceNotFoundException;
import com.iem.FilmRentalStore.repository.CityRepository;
import com.iem.FilmRentalStore.repository.CountryRepository;
import com.iem.FilmRentalStore.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepo;
    private final CountryRepository countryRepo;

    @Override
    public CityDTO.Response create(CityDTO.Request dto) {
        if (dto.getCountryId() == null) {
            throw new IllegalArgumentException("countryId is required");
        }

        Country country = countryRepo.findById(dto.getCountryId())
                .orElseThrow(() -> new ResourceNotFoundException("Country", "countryId", dto.getCountryId()));

        City city = CityDTO.toEntity(dto, country);

        return CityDTO.toResponse(cityRepo.save(city));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CityDTO.Response> getAll() {
        return cityRepo.findAll()
                .stream()
                .map(CityDTO::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CityDTO.Response getById(Short id) {
        City city = cityRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found"));

        return CityDTO.toResponse(city);
    }

    @Override
    public CityDTO.Response update(Short id, CityDTO.Request dto) {
        if (dto.getCountryId() == null) {
            throw new IllegalArgumentException("countryId is required");
        }

        City city = cityRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City", "cityId", id));

        Country country = countryRepo.findById(dto.getCountryId())
                .orElseThrow(() -> new ResourceNotFoundException("Country", "countryId", dto.getCountryId()));

        city.setCity(dto.getCity());
        city.setCountry(country);

        return CityDTO.toResponse(cityRepo.save(city));
    }

    @Override
    public void delete(Short id) {
        cityRepo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CityDTO.Response> getByCountry(Short countryId) {
        return cityRepo.findByCountry_CountryId(countryId)
                .stream()
                .map(CityDTO::toResponse)
                .toList();
    }
}
