package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.address.AddressDTO;
import com.iem.FilmRentalStore.dto.address.AddressRequestDTO;
import com.iem.FilmRentalStore.dto.address.AddressResponseDTO;
import com.iem.FilmRentalStore.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressService {

    AddressDTO createAddress(AddressRequestDTO request);

    AddressResponseDTO getAddressById(Short id);

    Page<AddressResponseDTO> getAllAddresses(Pageable pageable);

    AddressDTO updateAddress(Short id, AddressRequestDTO request);

    Address createAndReturnEntity(AddressRequestDTO request);

    Page<AddressResponseDTO> getByCountry(String country, Pageable pageable);

    AddressDTO patchAddress(Short id, AddressRequestDTO request);

    Page<AddressResponseDTO> searchByAddress(String address, Pageable pageable);

    Page<AddressResponseDTO> searchByDistrict(String district, Pageable pageable);

    Page<AddressResponseDTO> searchByCity(String city, Pageable pageable);
}