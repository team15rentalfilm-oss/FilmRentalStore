package com.iem.FilmRentalStore.unit.service;

import com.iem.FilmRentalStore.dto.address.AddressDTO;
import com.iem.FilmRentalStore.dto.address.AddressRequestDTO;
import com.iem.FilmRentalStore.dto.city.CityDTO;
import com.iem.FilmRentalStore.dto.city.CityPatchDTO;
import com.iem.FilmRentalStore.dto.city.CityRequestDTO;
import com.iem.FilmRentalStore.dto.city.CityResponseDTO;
import com.iem.FilmRentalStore.dto.country.CountryDTO;
import com.iem.FilmRentalStore.dto.country.CountryRequestDTO;
import com.iem.FilmRentalStore.dto.language.LanguageRequestDTO;
import com.iem.FilmRentalStore.entity.Actor;
import com.iem.FilmRentalStore.entity.Address;
import com.iem.FilmRentalStore.entity.Category;
import com.iem.FilmRentalStore.entity.City;
import com.iem.FilmRentalStore.entity.Country;
import com.iem.FilmRentalStore.entity.Language;
import com.iem.FilmRentalStore.mapper.ActorMapper;
import com.iem.FilmRentalStore.mapper.CityMapper;
import com.iem.FilmRentalStore.mapper.CountryMapper;
import com.iem.FilmRentalStore.mapper.LanguageMapper;
import com.iem.FilmRentalStore.repository.ActorRepository;
import com.iem.FilmRentalStore.repository.AddressRepository;
import com.iem.FilmRentalStore.repository.CategoryRepository;
import com.iem.FilmRentalStore.repository.CityRepository;
import com.iem.FilmRentalStore.repository.CountryRepository;
import com.iem.FilmRentalStore.repository.LanguageRepository;
import com.iem.FilmRentalStore.service.CountryService;
import com.iem.FilmRentalStore.service.impl.ActorServiceImpl;
import com.iem.FilmRentalStore.service.impl.AddressServiceImpl;
import com.iem.FilmRentalStore.service.impl.CategoryServiceImpl;
import com.iem.FilmRentalStore.service.impl.CityServiceImpl;
import com.iem.FilmRentalStore.service.impl.CountryServiceImpl;
import com.iem.FilmRentalStore.service.impl.LanguageServiceImpl;
import com.iem.FilmRentalStore.unit.support.TestDataFactory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReferenceServicesUnitTest {

    @Mock private ActorRepository actorRepository;
    @Mock private CategoryRepository categoryRepository;
    @Mock private CountryRepository countryRepository;
    @Mock private CityRepository cityRepository;
    @Mock private AddressRepository addressRepository;
    @Mock private LanguageRepository languageRepository;
    @Mock private CountryService countryService;

    @Test
    void actorServiceCreatesAndSearchesActors() {
        ActorServiceImpl service = new ActorServiceImpl(actorRepository, new ActorMapper());
        Actor actor = TestDataFactory.actor((short) 1, "Tom", "Hanks");
        when(actorRepository.save(any(Actor.class))).thenReturn(actor);
        when(actorRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase("tom", "tom"))
                .thenReturn(List.of(actor));

        var created = service.createActor(TestDataFactory.actorRequest("Tom", "Hanks"));
        var results = service.searchActors("tom");

        assertThat(created.getActorId()).isEqualTo((short) 1);
        assertThat(created.getFirstName()).isEqualTo("Tom");
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getLastName()).isEqualTo("Hanks");
    }

    @Test
    void actorServiceThrowsWhenActorMissing() {
        ActorServiceImpl service = new ActorServiceImpl(actorRepository, new ActorMapper());
        when(actorRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getActorById(99))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Actor not found");
    }

    @Test
    void categoryServiceRejectsDuplicateCreateAndUpdatesExisting() {
        CategoryServiceImpl service = new CategoryServiceImpl(categoryRepository, new com.iem.FilmRentalStore.mapper.CategoryMapper());
        Category existing = TestDataFactory.category((byte) 1, "Drama");
        Category updated = TestDataFactory.category((byte) 1, "Action");
        when(categoryRepository.findByNameIgnoreCase("Drama")).thenReturn(Optional.of(existing));
        when(categoryRepository.findById((byte) 1)).thenReturn(Optional.of(existing));
        when(categoryRepository.findByNameIgnoreCase("Action")).thenReturn(Optional.empty());
        when(categoryRepository.save(existing)).thenReturn(updated);

        assertThatThrownBy(() -> service.createCategory(TestDataFactory.categoryRequest("Drama")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Category already exists");

        var response = service.updateCategory((byte) 1, TestDataFactory.categoryRequest(" Action "));

        assertThat(response.getName()).isEqualTo("Action");
    }

    @Test
    void countryServiceSearchesAndCreatesMissingCountry() {
        CountryServiceImpl service = new CountryServiceImpl(countryRepository, new CountryMapper());
        Country india = TestDataFactory.country((short) 1, "India");
        when(countryRepository.findByCountryContainingIgnoreCase("Ind")).thenReturn(List.of(india));
        when(countryRepository.findByCountryIgnoreCase("Australia")).thenReturn(Optional.empty());
        when(countryRepository.save(any(Country.class))).thenAnswer(invocation -> {
            Country saved = invocation.getArgument(0);
            saved.setCountryId((short) 2);
            return saved;
        });

        assertThat(service.searchCountries(" ")).isEmpty();
        assertThat(service.searchCountries("Ind")).hasSize(1);

        Country created = service.getOrCreateCountry(" Australia ");

        assertThat(created.getCountryId()).isEqualTo((short) 2);
        assertThat(created.getCountry()).isEqualTo("Australia");
    }

    @Test
    void cityServiceCreatesAndPatchesCity() {
        CityServiceImpl service = new CityServiceImpl(cityRepository, countryRepository, new CityMapper(), countryService);
        Country india = TestDataFactory.country((short) 1, "India");
        Country australia = TestDataFactory.country((short) 2, "Australia");
        City existing = TestDataFactory.city((short) 1, "Bengaluru", india);
        City fetched = TestDataFactory.city((short) 1, "Sydney", australia);
        CityRequestDTO request = TestDataFactory.cityRequest("Bengaluru", "India");
        CityPatchDTO patch = TestDataFactory.cityPatch("Sydney", "Australia");

        when(countryService.getOrCreateCountry("India")).thenReturn(india);
        when(cityRepository.findByCityIgnoreCaseAndCountry("Bengaluru", india)).thenReturn(Optional.empty());
        when(cityRepository.save(any(City.class))).thenReturn(existing).thenReturn(fetched);
        when(cityRepository.findById((short) 1)).thenReturn(Optional.of(existing));
        when(countryService.getOrCreateCountry("Australia")).thenReturn(australia);
        when(cityRepository.findByIdWithCountry((short) 1)).thenReturn(Optional.of(fetched));

        CityDTO created = service.createCity(request);
        CityResponseDTO patched = service.patchCity((short) 1, patch);

        assertThat(created.getCity()).isEqualTo("Bengaluru");
        assertThat(patched.getCity()).isEqualTo("Sydney");
        assertThat(patched.getCountry().getCountry()).isEqualTo("Australia");
    }

    @Test
    void cityServiceRejectsDuplicateCityCreation() {
        CityServiceImpl service = new CityServiceImpl(cityRepository, countryRepository, new CityMapper(), countryService);
        Country india = TestDataFactory.country((short) 1, "India");
        when(countryService.getOrCreateCountry("India")).thenReturn(india);
        when(cityRepository.findByCityIgnoreCaseAndCountry("Bengaluru", india))
                .thenReturn(Optional.of(TestDataFactory.city((short) 1, "Bengaluru", india)));

        assertThatThrownBy(() -> service.createCity(TestDataFactory.cityRequest("Bengaluru", "India")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("City already exists");
    }

    @Test
    void addressServiceCreatesAddressWithResolvedCityAndCountry() {
        AddressServiceImpl service = new AddressServiceImpl(addressRepository, cityRepository, countryRepository);
        AddressRequestDTO request = TestDataFactory.addressRequest();
        Country country = TestDataFactory.country((short) 1, "India");
        City city = TestDataFactory.city((short) 1, "Bengaluru", country);
        Address saved = TestDataFactory.address((short) 1, "221B Baker Street", city);

        when(countryRepository.findByCountryIgnoreCase("India")).thenReturn(Optional.of(country));
        when(cityRepository.findByCityIgnoreCaseAndCountry("Bengaluru", country)).thenReturn(Optional.of(city));
        when(addressRepository.save(any(Address.class))).thenReturn(saved);
        when(addressRepository.findByIdWithFetch((short) 1)).thenReturn(Optional.of(saved));

        AddressDTO dto = service.createAddress(request);

        assertThat(dto.getAddress()).isEqualTo("221B Baker Street");
        assertThat(dto.getCity().getCity()).isEqualTo("Bengaluru");
    }

    @Test
    void addressServicePatchesOnlyProvidedFields() {
        AddressServiceImpl service = new AddressServiceImpl(addressRepository, cityRepository, countryRepository);
        Country country = TestDataFactory.country((short) 1, "India");
        City city = TestDataFactory.city((short) 1, "Bengaluru", country);
        Address existing = TestDataFactory.address((short) 1, "221B Baker Street", city);
        AddressRequestDTO patch = new AddressRequestDTO();
        patch.setPhone("7777777777");

        when(addressRepository.findById((short) 1)).thenReturn(Optional.of(existing));
        when(addressRepository.save(existing)).thenReturn(existing);
        when(addressRepository.findByIdWithFetch((short) 1)).thenReturn(Optional.of(existing));

        AddressDTO dto = service.patchAddress((short) 1, patch);

        assertThat(dto.getPhone()).isEqualTo("7777777777");
        verify(countryRepository, never()).save(any(Country.class));
        verify(cityRepository, never()).save(any(City.class));
    }

    @Test
    void languageServiceHandlesDuplicateCreateAndUpdate() {
        LanguageServiceImpl service = new LanguageServiceImpl(languageRepository, new LanguageMapper());
        Language existing = TestDataFactory.language(1, "English");
        Language updated = TestDataFactory.language(1, "Hindi");
        when(languageRepository.findByNameIgnoreCase("English")).thenReturn(Optional.of(existing));
        when(languageRepository.findById(1)).thenReturn(Optional.of(existing));
        when(languageRepository.findByNameIgnoreCase("Hindi")).thenReturn(Optional.empty());
        when(languageRepository.save(existing)).thenReturn(updated);

        assertThatThrownBy(() -> service.createLanguage(TestDataFactory.languageRequest("English")))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Language already exists");

        var response = service.updateLanguage(1, TestDataFactory.languageRequest("Hindi"));

        assertThat(response.getName()).isEqualTo("Hindi");
    }
}
