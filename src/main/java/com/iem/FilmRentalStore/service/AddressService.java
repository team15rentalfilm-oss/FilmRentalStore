package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.AddressDTO;

import java.util.List;

public interface AddressService {

    AddressDTO.Response create(AddressDTO.Request dto);

    List<AddressDTO.Response> getAll();

    AddressDTO.Response getById(Short id);

    AddressDTO.Response update(Short id, AddressDTO.Request dto);

    void delete(Short id);

    List<AddressDTO.Response> getByCity(Short cityId);
}