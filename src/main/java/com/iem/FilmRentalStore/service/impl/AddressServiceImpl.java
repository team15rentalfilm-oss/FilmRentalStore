package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.AddressDTO;
import com.iem.FilmRentalStore.entity.Address;
import com.iem.FilmRentalStore.entity.City;
import com.iem.FilmRentalStore.repository.AddressRepository;
import com.iem.FilmRentalStore.repository.CityRepository;
import com.iem.FilmRentalStore.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepo;
    private final CityRepository cityRepo;
    private static final String DEFAULT_LOCATION_WKT = "POINT(0 0)";

    @Override
    public AddressDTO.Response create(AddressDTO.Request dto) {

        City city = cityRepo.findById(dto.getCityId())
                .orElseThrow(() -> new RuntimeException("City not found"));

        Address address = new Address();
        address.setAddress(dto.getAddress());
        address.setAddress2(dto.getAddress2());
        address.setDistrict(dto.getDistrict());
        address.setCity(city); // 🔥 KEY LINE
        address.setPostalCode(dto.getPostalCode());
        address.setPhone(dto.getPhone());
        address.setLocation(normalizeLocation(dto.getLocation()));

        return AddressDTO.toResponse(addressRepo.save(address));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressDTO.Response> getAll() {
        return addressRepo.findAll()
                .stream()
                .map(AddressDTO::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AddressDTO.Response getById(Short id) {
        Address address = addressRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        return AddressDTO.toResponse(address);
    }

    @Override
    public AddressDTO.Response update(Short id, AddressDTO.Request dto) {

        Address address = addressRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        City city = cityRepo.findById(dto.getCityId())
                .orElseThrow(() -> new RuntimeException("City not found"));

        address.setAddress(dto.getAddress());
        address.setAddress2(dto.getAddress2());
        address.setDistrict(dto.getDistrict());
        address.setCity(city); // 🔥 KEY LINE
        address.setPostalCode(dto.getPostalCode());
        address.setPhone(dto.getPhone());
        address.setLocation(normalizeLocation(dto.getLocation()));

        return AddressDTO.toResponse(addressRepo.save(address));
    }

    @Override
    public void delete(Short id) {
        addressRepo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressDTO.Response> getByCity(Short cityId) {
        return addressRepo.findByCity_CityId(cityId)
                .stream()
                .map(AddressDTO::toResponse)
                .toList();
    }

    private String normalizeLocation(String location) {
        return (location == null || location.isBlank()) ? DEFAULT_LOCATION_WKT : location;
    }
}
