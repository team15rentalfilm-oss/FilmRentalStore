package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.address.AddressDTO;
import com.iem.FilmRentalStore.dto.address.AddressRequestDTO;
import com.iem.FilmRentalStore.entity.Address;

import java.util.List;

public interface AddressService {

    AddressDTO createAddress(AddressRequestDTO request);

    AddressDTO getAddressById(Short id);

    List<AddressDTO> getAllAddresses();

    AddressDTO updateAddress(Short id, AddressRequestDTO request);

    Address createAndReturnEntity(AddressRequestDTO request);

    List<AddressDTO> getByCountry(String country);

    AddressDTO patchAddress(Short id, AddressRequestDTO request);

    List<AddressDTO> searchByAddress(String address);

    List<AddressDTO> searchByDistrict(String district);

    List<AddressDTO> searchByCity(String city);
}