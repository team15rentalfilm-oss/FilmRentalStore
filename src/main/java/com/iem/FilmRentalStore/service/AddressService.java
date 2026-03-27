package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.address.AddressDTO;
import com.iem.FilmRentalStore.dto.address.AddressRequestDTO;

import java.util.List;

public interface AddressService {

    AddressDTO createAddress(AddressRequestDTO request);

    AddressDTO getAddressById(Short id);

    List<AddressDTO> getAllAddresses();

    AddressDTO updateAddress(Short id, AddressRequestDTO request);
}