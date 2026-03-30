package com.iem.FilmRentalStore.unit.controller;

import com.iem.FilmRentalStore.controller.ActorController;
import com.iem.FilmRentalStore.controller.AddressController;
import com.iem.FilmRentalStore.controller.CategoryController;
import com.iem.FilmRentalStore.controller.CityController;
import com.iem.FilmRentalStore.controller.CountryController;
import com.iem.FilmRentalStore.controller.CustomerController;
import com.iem.FilmRentalStore.controller.FilmController;
import com.iem.FilmRentalStore.controller.InventoryController;
import com.iem.FilmRentalStore.controller.LanguageController;
import com.iem.FilmRentalStore.controller.PaymentController;
import com.iem.FilmRentalStore.controller.RentalController;
import com.iem.FilmRentalStore.controller.StaffController;
import com.iem.FilmRentalStore.controller.StoreController;
import com.iem.FilmRentalStore.dto.actor.ActorRequestDTO;
import com.iem.FilmRentalStore.dto.actor.ActorResponseDTO;
import com.iem.FilmRentalStore.dto.address.AddressDTO;
import com.iem.FilmRentalStore.dto.address.AddressRequestDTO;
import com.iem.FilmRentalStore.dto.category.CategoryDTO;
import com.iem.FilmRentalStore.dto.category.CategoryRequestDTO;
import com.iem.FilmRentalStore.dto.category.CategoryResponseDTO;
import com.iem.FilmRentalStore.dto.city.CityPatchDTO;
import com.iem.FilmRentalStore.dto.city.CityRequestDTO;
import com.iem.FilmRentalStore.dto.city.CityResponseDTO;
import com.iem.FilmRentalStore.dto.country.CountryDTO;
import com.iem.FilmRentalStore.dto.country.CountryRequestDTO;
import com.iem.FilmRentalStore.dto.country.CountryResponseDTO;
import com.iem.FilmRentalStore.dto.customer.CustomerDTO;
import com.iem.FilmRentalStore.dto.customer.CustomerPatchDTO;
import com.iem.FilmRentalStore.dto.customer.CustomerRequestDTO;
import com.iem.FilmRentalStore.dto.customer.CustomerResponseDTO;
import com.iem.FilmRentalStore.dto.film.FilmPatchDTO;
import com.iem.FilmRentalStore.dto.film.FilmRequestDTO;
import com.iem.FilmRentalStore.dto.film.FilmResponseDTO;
import com.iem.FilmRentalStore.dto.inventory.InventoryDTO;
import com.iem.FilmRentalStore.dto.inventory.InventoryRequestDTO;
import com.iem.FilmRentalStore.dto.inventory.InventoryResponseDTO;
import com.iem.FilmRentalStore.dto.language.LanguageRequestDTO;
import com.iem.FilmRentalStore.dto.language.LanguageResponseDTO;
import com.iem.FilmRentalStore.dto.payment.PaymentPatchDTO;
import com.iem.FilmRentalStore.dto.payment.PaymentRequestDTO;
import com.iem.FilmRentalStore.dto.payment.PaymentResponseDTO;
import com.iem.FilmRentalStore.dto.rental.RentalRequestDTO;
import com.iem.FilmRentalStore.dto.rental.RentalResponseDTO;
import com.iem.FilmRentalStore.dto.staff.StaffPatchDTO;
import com.iem.FilmRentalStore.dto.staff.StaffRequestDTO;
import com.iem.FilmRentalStore.dto.staff.StaffResponseDTO;
import com.iem.FilmRentalStore.dto.store.StoreDTO;
import com.iem.FilmRentalStore.dto.store.StoreRequestDTO;
import com.iem.FilmRentalStore.dto.store.StoreResponseDTO;
import com.iem.FilmRentalStore.service.ActorService;
import com.iem.FilmRentalStore.service.AddressService;
import com.iem.FilmRentalStore.service.CategoryService;
import com.iem.FilmRentalStore.service.CityService;
import com.iem.FilmRentalStore.service.CountryService;
import com.iem.FilmRentalStore.service.CustomerService;
import com.iem.FilmRentalStore.service.FilmService;
import com.iem.FilmRentalStore.service.InventoryService;
import com.iem.FilmRentalStore.service.LanguageService;
import com.iem.FilmRentalStore.service.PaymentService;
import com.iem.FilmRentalStore.service.RentalService;
import com.iem.FilmRentalStore.service.StaffService;
import com.iem.FilmRentalStore.service.StoreService;
import com.iem.FilmRentalStore.unit.support.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ControllerUnitTest {

    @Mock private ActorService actorService;
    @Mock private AddressService addressService;
    @Mock private CategoryService categoryService;
    @Mock private CityService cityService;
    @Mock private CountryService countryService;
    @Mock private CustomerService customerService;
    @Mock private FilmService filmService;
    @Mock private InventoryService inventoryService;
    @Mock private LanguageService languageService;
    @Mock private PaymentService paymentService;
    @Mock private RentalService rentalService;
    @Mock private StaffService staffService;
    @Mock private StoreService storeService;

    @InjectMocks private ActorController actorController;
    @InjectMocks private AddressController addressController;
    @InjectMocks private CategoryController categoryController;
    @InjectMocks private CityController cityController;
    @InjectMocks private CountryController countryController;
    @InjectMocks private CustomerController customerController;
    @InjectMocks private FilmController filmController;
    @InjectMocks private InventoryController inventoryController;
    @InjectMocks private LanguageController languageController;
    @InjectMocks private PaymentController paymentController;
    @InjectMocks private RentalController rentalController;
    @InjectMocks private StaffController staffController;
    @InjectMocks private StoreController storeController;

    @Test
    void actorControllerDelegatesAllEndpoints() {
        ActorRequestDTO request = TestDataFactory.actorRequest("Tom", "Hanks");
        ActorResponseDTO response = new ActorResponseDTO();
        when(actorService.createActor(request)).thenReturn(response);
        when(actorService.getActorById(1)).thenReturn(response);
        when(actorService.getAllActors()).thenReturn(List.of(response));
        when(actorService.searchActors("tom")).thenReturn(List.of(response));
        when(actorService.updateActor(1, request)).thenReturn(response);

        assertThat(actorController.createActor(request)).isSameAs(response);
        assertThat(actorController.getActorById(1)).isSameAs(response);
        assertThat(actorController.getAllActors()).containsExactly(response);
        assertThat(actorController.searchActors("tom")).containsExactly(response);
        assertThat(actorController.updateActor(1, request)).isSameAs(response);
    }

    @Test
    void addressControllerDelegatesAllEndpoints() {
        AddressRequestDTO request = TestDataFactory.addressRequest();
        AddressDTO dto = new AddressDTO();
        when(addressService.createAddress(request)).thenReturn(dto);
        when(addressService.getAddressById((short) 1)).thenReturn(dto);
        when(addressService.getAllAddresses()).thenReturn(List.of(dto));
        when(addressService.updateAddress((short) 1, request)).thenReturn(dto);
        when(addressService.patchAddress((short) 1, request)).thenReturn(dto);
        when(addressService.getByCountry("India")).thenReturn(List.of(dto));
        when(addressService.searchByAddress("221")).thenReturn(List.of(dto));
        when(addressService.searchByDistrict("Central")).thenReturn(List.of(dto));
        when(addressService.searchByCity("Bengaluru")).thenReturn(List.of(dto));

        assertThat(addressController.createAddress(request)).isSameAs(dto);
        assertThat(addressController.getAddressById((short) 1)).isSameAs(dto);
        assertThat(addressController.getAllAddresses()).containsExactly(dto);
        assertThat(addressController.updateAddress((short) 1, request)).isSameAs(dto);
        assertThat(addressController.patchAddress((short) 1, request)).isSameAs(dto);
        assertThat(addressController.getByCountry("India")).containsExactly(dto);
        assertThat(addressController.searchByAddress("221")).containsExactly(dto);
        assertThat(addressController.searchByDistrict("Central")).containsExactly(dto);
        assertThat(addressController.searchByCity("Bengaluru")).containsExactly(dto);
    }

    @Test
    void categoryControllerDelegatesAllEndpoints() {
        CategoryRequestDTO request = TestDataFactory.categoryRequest("Drama");
        CategoryDTO dto = new CategoryDTO();
        CategoryResponseDTO response = new CategoryResponseDTO();
        when(categoryService.createCategory(request)).thenReturn(dto);
        when(categoryService.getCategoryResponseById((byte) 1)).thenReturn(response);
        when(categoryService.getAllCategoryResponses()).thenReturn(List.of(response));
        when(categoryService.updateCategory((byte) 1, request)).thenReturn(dto);

        assertThat(categoryController.createCategory(request)).isSameAs(dto);
        assertThat(categoryController.getCategoryById((byte) 1)).isSameAs(response);
        assertThat(categoryController.getAllCategories()).containsExactly(response);
        assertThat(categoryController.updateCategory((byte) 1, request)).isSameAs(dto);
    }

    @Test
    void cityControllerDelegatesAllEndpoints() {
        CityRequestDTO request = TestDataFactory.cityRequest("Bengaluru", "India");
        CityPatchDTO patch = TestDataFactory.cityPatch("Mumbai", "India");
        CityResponseDTO response = new CityResponseDTO();
        when(cityService.createCity(request)).thenReturn(new com.iem.FilmRentalStore.dto.city.CityDTO());
        when(cityService.updateCity((short) 1, request)).thenReturn(response);
        when(cityService.getCityById((short) 1)).thenReturn(response);
        when(cityService.getAllCities()).thenReturn(List.of(response));
        when(cityService.searchCitiesByName("Ben")).thenReturn(List.of(response));
        when(cityService.searchCitiesByCountry("India")).thenReturn(List.of(response));
        when(cityService.patchCity((short) 1, patch)).thenReturn(response);

        assertThat(cityController.createCity(request)).isNotNull();
        assertThat(cityController.updateCity((short) 1, request)).isSameAs(response);
        assertThat(cityController.getCityById((short) 1)).isSameAs(response);
        assertThat(cityController.getAllCities()).containsExactly(response);
        assertThat(cityController.searchCities("Ben")).containsExactly(response);
        assertThat(cityController.searchCitiesByCountry("India")).containsExactly(response);
        assertThat(cityController.patchCity((short) 1, patch)).isSameAs(response);
    }

    @Test
    void countryControllerDelegatesAllEndpoints() {
        CountryRequestDTO request = TestDataFactory.countryRequest("India");
        CountryDTO dto = new CountryDTO();
        CountryResponseDTO response = new CountryResponseDTO();
        when(countryService.createCountry(request)).thenReturn(dto);
        when(countryService.getCountryById((short) 1)).thenReturn(response);
        when(countryService.getAllCountries()).thenReturn(List.of(response));
        when(countryService.searchCountries("Ind")).thenReturn(List.of(response));
        when(countryService.updateCountry((short) 1, request)).thenReturn(dto);

        assertThat(countryController.createCountry(request)).isSameAs(dto);
        assertThat(countryController.getCountryById((short) 1)).isSameAs(response);
        assertThat(countryController.getAllCountries()).containsExactly(response);
        assertThat(countryController.searchCountries("Ind")).containsExactly(response);
        assertThat(countryController.updateCountry((short) 1, request)).isSameAs(dto);
    }

    @Test
    void customerControllerDelegatesAllEndpoints() {
        CustomerRequestDTO request = TestDataFactory.customerRequest();
        CustomerPatchDTO patch = TestDataFactory.customerPatch();
        CustomerDTO dto = new CustomerDTO();
        CustomerResponseDTO response = new CustomerResponseDTO();
        when(customerService.createCustomer(request)).thenReturn(dto);
        when(customerService.getCustomerById((short) 1)).thenReturn(response);
        when(customerService.getAllCustomers()).thenReturn(List.of(dto));
        when(customerService.updateCustomer((short) 1, request)).thenReturn(dto);
        when(customerService.getByFirstName("John")).thenReturn(List.of(dto));
        when(customerService.getByLastName("Doe")).thenReturn(List.of(dto));
        when(customerService.getByEmail("john")).thenReturn(List.of(dto));
        when(customerService.getByActive(true)).thenReturn(List.of(dto));
        when(customerService.getByCity("Bengaluru")).thenReturn(List.of(dto));
        when(customerService.getByCountry("India")).thenReturn(List.of(dto));
        when(customerService.patchCustomer((short) 1, patch)).thenReturn(dto);

        assertThat(customerController.createCustomer(request)).isSameAs(dto);
        assertThat(customerController.getCustomer((short) 1)).isSameAs(response);
        assertThat(customerController.getCustomers()).containsExactly(dto);
        assertThat(customerController.updateCustomer((short) 1, request)).isSameAs(dto);
        assertThat(customerController.getByFirstName("John")).containsExactly(dto);
        assertThat(customerController.getByLastName("Doe")).containsExactly(dto);
        assertThat(customerController.getByEmail("john")).containsExactly(dto);
        assertThat(customerController.getByActive(true)).containsExactly(dto);
        assertThat(customerController.getByCity("Bengaluru")).containsExactly(dto);
        assertThat(customerController.getByCountry("India")).containsExactly(dto);
        assertThat(customerController.patchCustomer((short) 1, patch)).isSameAs(dto);
    }

    @Test
    void filmControllerDelegatesAllEndpoints() {
        FilmRequestDTO request = TestDataFactory.filmRequest();
        FilmPatchDTO patch = TestDataFactory.filmPatch();
        FilmResponseDTO response = new FilmResponseDTO();
        Page<FilmResponseDTO> page = new PageImpl<>(List.of(response));
        when(filmService.createFilm(request)).thenReturn(response);
        when(filmService.getFilmById((short) 1)).thenReturn(response);
        when(filmService.getAllFilms(0, 10)).thenReturn(page);
        when(filmService.updateFilm((short) 1, request)).thenReturn(response);
        when(filmService.patchFilm((short) 1, patch)).thenReturn(response);
        when(filmService.searchFilms("Inception", 2010, "Sci-Fi", "Leonardo DiCaprio", 0, 10)).thenReturn(page);

        assertThat(filmController.createFilm(request)).isSameAs(response);
        assertThat(filmController.getFilmById((short) 1)).isSameAs(response);
        assertThat(filmController.getAllFilms(0, 10)).isSameAs(page);
        assertThat(filmController.updateFilm((short) 1, request)).isSameAs(response);
        assertThat(filmController.patchFilm((short) 1, patch)).isSameAs(response);
        assertThat(filmController.searchFilms("Inception", 2010, "Sci-Fi", "Leonardo DiCaprio", 0, 10)).isSameAs(page);
    }

    @Test
    void inventoryControllerSanitizesPageableBeforeDelegating() {
        InventoryRequestDTO request = TestDataFactory.inventoryRequest((short) 1, (short) 1);
        InventoryDTO dto = new InventoryDTO();
        InventoryResponseDTO response = new InventoryResponseDTO();
        Page<InventoryDTO> page = new PageImpl<>(List.of(dto));
        Pageable incoming = PageRequest.of(2, 5, Sort.by("film.title"));
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

        when(inventoryService.createInventory(request)).thenReturn(dto);
        when(inventoryService.getInventoryById(1)).thenReturn(dto);
        when(inventoryService.getInventoryDetails(1)).thenReturn(response);
        when(inventoryService.getAllInventory(eq((short) 1), eq((short) 1), any(Pageable.class))).thenReturn(page);
        when(inventoryService.updateInventory(1, request)).thenReturn(dto);
        when(inventoryService.patchInventory(1, request)).thenReturn(dto);
        when(inventoryService.getByFilmId(eq((short) 1), any(Pageable.class))).thenReturn(page);
        when(inventoryService.getByStoreId(eq((short) 1), any(Pageable.class))).thenReturn(page);

        assertThat(inventoryController.createInventory(request).getBody()).isSameAs(dto);
        assertThat(inventoryController.getInventoryById(1).getBody()).isSameAs(dto);
        assertThat(inventoryController.getInventoryDetails(1).getBody()).isSameAs(response);
        assertThat(inventoryController.getAllInventory((short) 1, (short) 1, incoming).getBody()).isSameAs(page);
        assertThat(inventoryController.updateInventory(1, request).getBody()).isSameAs(dto);
        assertThat(inventoryController.patchInventory(1, request).getBody()).isSameAs(dto);
        assertThat(inventoryController.getByFilmId((short) 1, incoming).getBody()).isSameAs(page);
        assertThat(inventoryController.getByStoreId((short) 1, incoming).getBody()).isSameAs(page);

        verify(inventoryService).getAllInventory(eq((short) 1), eq((short) 1), pageableCaptor.capture());
        assertThat(pageableCaptor.getValue().getSort().isUnsorted()).isTrue();
        assertThat(pageableCaptor.getValue().getPageNumber()).isEqualTo(2);
        assertThat(pageableCaptor.getValue().getPageSize()).isEqualTo(5);
    }

    @Test
    void languageControllerDelegatesAllEndpoints() {
        LanguageRequestDTO request = TestDataFactory.languageRequest("English");
        LanguageResponseDTO response = new LanguageResponseDTO();
        when(languageService.createLanguage(request)).thenReturn(response);
        when(languageService.getLanguageById(1)).thenReturn(response);
        when(languageService.getAllLanguages()).thenReturn(List.of(response));
        when(languageService.getLanguageByName("English")).thenReturn(response);
        when(languageService.updateLanguage(1, request)).thenReturn(response);

        assertThat(languageController.createLanguage(request)).isSameAs(response);
        assertThat(languageController.getLanguageById(1)).isSameAs(response);
        assertThat(languageController.getAllLanguages()).containsExactly(response);
        assertThat(languageController.getLanguageByName("English")).isSameAs(response);
        assertThat(languageController.updateLanguage(1, request)).isSameAs(response);
    }

    @Test
    void paymentControllerDelegatesAllEndpoints() {
        PaymentRequestDTO request = TestDataFactory.paymentRequest(1, (short) 1);
        PaymentPatchDTO patch = TestDataFactory.paymentPatch((short) 1);
        PaymentResponseDTO response = new PaymentResponseDTO();
        Page<PaymentResponseDTO> page = new PageImpl<>(List.of(response));
        Pageable pageable = PageRequest.of(0, 10);
        when(paymentService.createPayment(request)).thenReturn(response);
        when(paymentService.getPaymentById(1)).thenReturn(response);
        when(paymentService.getAllPayments(pageable)).thenReturn(page);
        when(paymentService.patchPayment(1, patch)).thenReturn(response);
        when(paymentService.getPaymentsByCustomerName("John")).thenReturn(List.of(response));
        when(paymentService.getPaymentsByRentalId(1)).thenReturn(List.of(response));
        when(paymentService.getByCustomerId((short) 1, pageable)).thenReturn(page);
        when(paymentService.getByStaffId((byte) 1, pageable)).thenReturn(page);
        when(paymentService.searchByStaffName("Mike", pageable)).thenReturn(page);

        assertThat(paymentController.createPayment(request)).isSameAs(response);
        assertThat(paymentController.getPaymentById(1)).isSameAs(response);
        assertThat(paymentController.getAllPayments(pageable)).isSameAs(page);
        assertThat(paymentController.patchPayment(1, patch)).isSameAs(response);
        assertThat(paymentController.getByCustomer("John")).containsExactly(response);
        assertThat(paymentController.getByRental(1)).containsExactly(response);
        assertThat(paymentController.getByCustomerId((short) 1, pageable)).isSameAs(page);
        assertThat(paymentController.getByStaffId((byte) 1, pageable)).isSameAs(page);
        assertThat(paymentController.searchByStaff("Mike", pageable)).isSameAs(page);
    }

    @Test
    void rentalControllerDelegatesAllEndpoints() {
        RentalRequestDTO request = TestDataFactory.rentalRequest(1, (short) 1, (short) 1);
        RentalResponseDTO response = new RentalResponseDTO();
        Page<RentalResponseDTO> page = new PageImpl<>(List.of(response));
        Pageable pageable = PageRequest.of(0, 10);
        when(rentalService.createRental(request)).thenReturn(response);
        when(rentalService.returnRental(1)).thenReturn(response);
        when(rentalService.getRentalById(1)).thenReturn(response);
        when(rentalService.getAllRentals(pageable)).thenReturn(page);
        when(rentalService.getByCustomerName("John")).thenReturn(List.of(response));
        when(rentalService.getByCustomerId((short) 1, pageable)).thenReturn(page);
        when(rentalService.getByInventoryId(1, pageable)).thenReturn(page);
        when(rentalService.getByStaffId((byte) 1, pageable)).thenReturn(page);

        assertThat(rentalController.createRental(request)).isSameAs(response);
        assertThat(rentalController.returnRental(1)).isSameAs(response);
        assertThat(rentalController.getRentalById(1)).isSameAs(response);
        assertThat(rentalController.getAllRentals(pageable)).isSameAs(page);
        assertThat(rentalController.getByCustomerName("John")).containsExactly(response);
        assertThat(rentalController.getByCustomerId((short) 1, pageable)).isSameAs(page);
        assertThat(rentalController.getByInventoryId(1, pageable)).isSameAs(page);
        assertThat(rentalController.getByStaffId((byte) 1, pageable)).isSameAs(page);
    }

    @Test
    void staffControllerSanitizesInvalidSortsBeforeDelegating() {
        StaffRequestDTO request = TestDataFactory.staffRequest();
        StaffPatchDTO patch = TestDataFactory.staffPatch();
        StaffResponseDTO response = new StaffResponseDTO();
        Page<StaffResponseDTO> page = new PageImpl<>(List.of(response));
        Pageable invalid = PageRequest.of(0, 10, Sort.by("sort[0]"));
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

        when(staffService.createStaff(request)).thenReturn(response);
        when(staffService.getStaffById((short) 1)).thenReturn(response);
        when(staffService.getAllStaff(any(Pageable.class))).thenReturn(page);
        when(staffService.searchStaff(eq("Mike"), eq((short) 1), any(Pageable.class))).thenReturn(page);
        when(staffService.getStaffByCity(eq("Bengaluru"), any(Pageable.class))).thenReturn(page);
        when(staffService.getStaffByCountry(eq("India"), any(Pageable.class))).thenReturn(page);
        when(staffService.updateStaff((short) 1, request)).thenReturn(response);
        when(staffService.patchStaff((short) 1, patch)).thenReturn(response);

        assertThat(staffController.create(request)).isSameAs(response);
        assertThat(staffController.getById((short) 1)).isSameAs(response);
        assertThat(staffController.getAll(invalid)).isSameAs(page);
        assertThat(staffController.search("Mike", (short) 1, invalid)).isSameAs(page);
        assertThat(staffController.getByCity("Bengaluru", invalid)).isSameAs(page);
        assertThat(staffController.getByCountry("India", invalid)).isSameAs(page);
        assertThat(staffController.update((short) 1, request)).isSameAs(response);
        assertThat(staffController.patch((short) 1, patch)).isSameAs(response);

        verify(staffService).getAllStaff(pageableCaptor.capture());
        assertThat(pageableCaptor.getValue().getSort().getOrderFor("staffId")).isNotNull();
    }

    @Test
    void storeControllerDelegatesAllEndpoints() {
        StoreRequestDTO request = TestDataFactory.storeRequest();
        StoreDTO dto = new StoreDTO();
        StoreResponseDTO response = new StoreResponseDTO();
        when(storeService.createStore(request)).thenReturn(response);
        when(storeService.getStoreById((short) 1)).thenReturn(dto);
        when(storeService.getAllStores()).thenReturn(List.of(dto));
        when(storeService.updateStore((short) 1, request)).thenReturn(dto);

        assertThat(storeController.createStore(request)).isSameAs(response);
        assertThat(storeController.getStoreById((short) 1)).isSameAs(dto);
        assertThat(storeController.getAllStores()).containsExactly(dto);
        assertThat(storeController.updateStore((short) 1, request)).isSameAs(dto);
    }
}
