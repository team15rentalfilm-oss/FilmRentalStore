package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.address.AddressDTO;
import com.iem.FilmRentalStore.dto.address.AddressRequestDTO;
import com.iem.FilmRentalStore.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressService {

    AddressDTO createAddress(AddressRequestDTO request);

    AddressDTO getAddressById(Short id);

    Page<AddressDTO> getAllAddresses(Pageable pageable);

    AddressDTO updateAddress(Short id, AddressRequestDTO request);

    Address createAndReturnEntity(AddressRequestDTO request);

    Page<AddressDTO> getByCountry(String country, Pageable pageable);

    AddressDTO patchAddress(Short id, AddressRequestDTO request);

    Page<AddressDTO> searchByAddress(String address, Pageable pageable);

    Page<AddressDTO> searchByDistrict(String district, Pageable pageable);

    Page<AddressDTO> searchByCity(String city, Pageable pageable);
}