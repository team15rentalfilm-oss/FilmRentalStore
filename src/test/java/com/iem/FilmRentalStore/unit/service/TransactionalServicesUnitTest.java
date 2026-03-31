package com.iem.FilmRentalStore.unit.service;

import com.iem.FilmRentalStore.dto.customer.CustomerPatchDTO;
import com.iem.FilmRentalStore.dto.customer.CustomerRequestDTO;
import com.iem.FilmRentalStore.dto.film.FilmRequestDTO;
import com.iem.FilmRentalStore.dto.payment.PaymentPatchDTO;
import com.iem.FilmRentalStore.dto.payment.PaymentRequestDTO;
import com.iem.FilmRentalStore.dto.rental.RentalPatchDTO;
import com.iem.FilmRentalStore.dto.rental.RentalRequestDTO;
import com.iem.FilmRentalStore.dto.staff.StaffPatchDTO;
import com.iem.FilmRentalStore.dto.staff.StaffRequestDTO;
import com.iem.FilmRentalStore.dto.store.StoreRequestDTO;
import com.iem.FilmRentalStore.entity.Address;
import com.iem.FilmRentalStore.entity.City;
import com.iem.FilmRentalStore.entity.Country;
import com.iem.FilmRentalStore.entity.Customer;
import com.iem.FilmRentalStore.entity.Film;
import com.iem.FilmRentalStore.entity.Inventory;
import com.iem.FilmRentalStore.entity.Language;
import com.iem.FilmRentalStore.entity.Payment;
import com.iem.FilmRentalStore.entity.Rental;
import com.iem.FilmRentalStore.entity.Staff;
import com.iem.FilmRentalStore.entity.Store;
import com.iem.FilmRentalStore.exception.ResourceNotFoundException;
import com.iem.FilmRentalStore.repository.ActorRepository;
import com.iem.FilmRentalStore.repository.AddressRepository;
import com.iem.FilmRentalStore.repository.CityRepository;
import com.iem.FilmRentalStore.repository.CountryRepository;
import com.iem.FilmRentalStore.repository.CustomerRepository;
import com.iem.FilmRentalStore.repository.FilmRepository;
import com.iem.FilmRentalStore.repository.InventoryRepository;
import com.iem.FilmRentalStore.repository.LanguageRepository;
import com.iem.FilmRentalStore.repository.PaymentRepository;
import com.iem.FilmRentalStore.repository.RentalRepository;
import com.iem.FilmRentalStore.repository.StaffRepository;
import com.iem.FilmRentalStore.repository.StoreRepository;
import com.iem.FilmRentalStore.service.AddressService;
import com.iem.FilmRentalStore.service.CityService;
import com.iem.FilmRentalStore.service.CountryService;
import com.iem.FilmRentalStore.service.impl.CustomerServiceImpl;
import com.iem.FilmRentalStore.service.impl.FilmServiceImpl;
import com.iem.FilmRentalStore.service.impl.InventoryServiceImpl;
import com.iem.FilmRentalStore.service.impl.PaymentServiceImpl;
import com.iem.FilmRentalStore.service.impl.RentalServiceImpl;
import com.iem.FilmRentalStore.service.impl.StaffServiceImpl;
import com.iem.FilmRentalStore.service.impl.StoreServiceImpl;
import com.iem.FilmRentalStore.unit.support.TestDataFactory;
import com.iem.FilmRentalStore.unit.support.TestDataFactory.PageData;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionalServicesUnitTest {

    @Mock private CustomerRepository customerRepository;
    @Mock private StoreRepository storeRepository;
    @Mock private AddressService addressService;
    @Mock private CountryRepository countryRepository;
    @Mock private CityRepository cityRepository;
    @Mock private AddressRepository addressRepository;
    @Mock private CountryService countryService;
    @Mock private CityService cityService;
    @Mock private FilmRepository filmRepository;
    @Mock private LanguageRepository languageRepository;
    @Mock private com.iem.FilmRentalStore.repository.CategoryRepository categoryRepository;
    @Mock private ActorRepository actorRepository;
    @Mock private InventoryRepository inventoryRepository;
    @Mock private PaymentRepository paymentRepository;
    @Mock private RentalRepository rentalRepository;
    @Mock private StaffRepository staffRepository;

    @Test
    void customerServiceCreatesAndPatchesCustomer() {
        CustomerServiceImpl service = new CustomerServiceImpl(
                customerRepository,
                storeRepository,
                addressService,
                addressRepository,
                countryService,
                cityService
        );
        PageData data = TestDataFactory.pageData();
        CustomerRequestDTO request = TestDataFactory.customerRequest();
        CustomerPatchDTO patch = TestDataFactory.customerPatch();
        Address replacementAddress = TestDataFactory.address((short) 2, "42 Wallaby Way", TestDataFactory.city((short) 2, "Sydney", TestDataFactory.country((short) 2, "Australia")));
        Store replacementStore = TestDataFactory.store(2, data.manager(), replacementAddress);
        Customer existing = data.customer();

        when(addressService.createAndReturnEntity(request.getAddress())).thenReturn(data.address());
        when(storeRepository.findById((short) 1)).thenReturn(Optional.of(data.store()));
        when(customerRepository.save(any(Customer.class))).thenAnswer(invocation -> {
            Customer saved = invocation.getArgument(0);
            saved.setCustomerId((short) 9);
            return saved;
        });
        when(customerRepository.findById((short) 1)).thenReturn(Optional.of(existing));
        when(storeRepository.findById((short) 2)).thenReturn(Optional.of(replacementStore));
        when(countryService.getOrCreateCountry("Australia")).thenReturn(TestDataFactory.country((short) 2, "Australia"));
        when(cityService.getOrCreateCity("Sydney", "Australia")).thenReturn(replacementAddress.getCity());
        when(addressRepository.findByAddressAndCity("42 Wallaby Way", replacementAddress.getCity())).thenReturn(Optional.of(replacementAddress));

        var created = service.createCustomer(request);
        var patched = service.patchCustomer((short) 1, patch);

        assertThat(created.getId()).isEqualTo((short) 9);
        assertThat(created.getFirstName()).isEqualTo("John");
        assertThat(patched.getFirstName()).isEqualTo("Jane");
        assertThat(existing.getStore().getStoreId()).isEqualTo(2);
    }

    @Test
    void customerServiceRejectsIncompleteAddressPatch() {
        CustomerServiceImpl service = new CustomerServiceImpl(
                customerRepository,
                storeRepository,
                addressService,
                addressRepository,
                countryService,
                cityService
        );
        Customer existing = TestDataFactory.pageData().customer();
        CustomerPatchDTO patch = new CustomerPatchDTO();
        patch.setCity("Sydney");
        when(customerRepository.findById((short) 1)).thenReturn(Optional.of(existing));

        assertThatThrownBy(() -> service.patchCustomer((short) 1, patch))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("City and Country must be provided together");
    }

    @Test
    void filmServiceCreatesAndSearchesFilms() {
        FilmServiceImpl service = new FilmServiceImpl(filmRepository, categoryRepository, languageRepository, actorRepository);
        PageData data = TestDataFactory.pageData();
        FilmRequestDTO request = TestDataFactory.filmRequest();
        Film film = data.film();
        Pageable pageable = PageRequest.of(0, 10);
        Pageable sanitizedPageable = PageRequest.of(0, 10, Sort.by("title").ascending());
        when(languageRepository.findByNameIgnoreCase("English")).thenReturn(Optional.of(data.language()));
        when(categoryRepository.findByNameIgnoreCase("Sci-Fi")).thenReturn(Optional.of(data.category()));
        when(actorRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase("Leonardo", "DiCaprio")).thenReturn(Optional.of(data.actor()));
        when(filmRepository.save(any(Film.class))).thenReturn(film);
        when(filmRepository.searchFilms("incep", 2010, "Sci-Fi", "Leonardo DiCaprio", sanitizedPageable))
                .thenReturn(new PageImpl<>(List.of(film)));

        var created = service.createFilm(request);
        var search = service.searchFilms("incep", 2010, "Sci-Fi", "Leonardo DiCaprio", pageable);

        assertThat(created.getFilmId()).isEqualTo((short) 1);
        assertThat(search.getContent()).hasSize(1);
        assertThat(search.getContent().get(0).getTitle()).isEqualTo("Inception");
    }

    @Test
    void filmServiceForcesTenItemsPerPage() {
        FilmServiceImpl service = new FilmServiceImpl(filmRepository, categoryRepository, languageRepository, actorRepository);
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

        when(filmRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of()));

        service.getAllFilms(PageRequest.of(0, 51, Sort.by("releaseYear")));

        verify(filmRepository).findAll(pageableCaptor.capture());
        assertThat(pageableCaptor.getValue().getPageNumber()).isEqualTo(0);
        assertThat(pageableCaptor.getValue().getPageSize()).isEqualTo(10);
        assertThat(pageableCaptor.getValue().getSort().getOrderFor("releaseYear")).isNotNull();
    }

    @Test
    void filmServiceThrowsWhenFilmMissing() {
        FilmServiceImpl service = new FilmServiceImpl(filmRepository, categoryRepository, languageRepository, actorRepository);
        when(filmRepository.findWithRelationsByFilmId((short) 99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getFilmById((short) 99))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Film");
    }

    @Test
    void filmServicePatchUpdatesRelationsAndScalarFields() {
        FilmServiceImpl service = new FilmServiceImpl(filmRepository, categoryRepository, languageRepository, actorRepository);
        PageData data = TestDataFactory.pageData();
        Film existing = data.film();
        var patch = TestDataFactory.filmPatch();
        Language spanish = TestDataFactory.language(2, "Spanish");
        var thriller = TestDataFactory.category((byte) 2, "Thriller");
        var bradPitt = TestDataFactory.actor((short) 2, "Brad", "Pitt");

        when(filmRepository.findWithRelationsByFilmId((short) 1)).thenReturn(Optional.of(existing));
        when(languageRepository.findByNameIgnoreCase("Spanish")).thenReturn(Optional.of(spanish));
        when(categoryRepository.findByNameIgnoreCase("Thriller")).thenReturn(Optional.of(thriller));
        when(actorRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase("Brad", "Pitt")).thenReturn(Optional.of(bradPitt));
        when(filmRepository.save(existing)).thenReturn(existing);

        var response = service.patchFilm((short) 1, patch);

        assertThat(response.getTitle()).isEqualTo("Updated Title");
        assertThat(existing.getLanguage().getName()).isEqualTo("Spanish");
        assertThat(existing.getCategories()).extracting("name").containsExactly("Thriller");
        assertThat(existing.getActors()).extracting("firstName", "lastName").containsExactly(tuple("Brad", "Pitt"));
        assertThat(existing.getRentalDuration()).isEqualTo(10);
        assertThat(existing.getRentalRate()).isEqualByComparingTo(BigDecimal.valueOf(5.99));
        assertThat(existing.getLength()).isEqualTo(150);
        assertThat(existing.getReplacementCost()).isEqualByComparingTo(BigDecimal.valueOf(24.99));
        assertThat(Set.of(existing.getSpecialFeatures().split(",")))
                .containsExactlyInAnyOrder("Deleted Scenes", "Commentary");
    }

    @Test
    void inventoryServiceSupportsFilteredFetchAndPatch() {
        InventoryServiceImpl service = new InventoryServiceImpl(inventoryRepository, filmRepository, storeRepository);
        PageData data = TestDataFactory.pageData();
        Inventory existing = data.inventory();
        Page<Inventory> inventoryPage = new PageImpl<>(List.of(existing));

        when(inventoryRepository.findByFilmAndStoreWithFetch((short) 1, (short) 1, PageRequest.of(0, 10))).thenReturn(inventoryPage);
        when(inventoryRepository.findById(1)).thenReturn(Optional.of(existing));
        when(filmRepository.findById((short) 1)).thenReturn(Optional.of(data.film()));
        when(storeRepository.findById((short) 1)).thenReturn(Optional.of(data.store()));
        when(inventoryRepository.save(existing)).thenReturn(existing);
        when(inventoryRepository.findByIdWithFilmAndStore(1)).thenReturn(Optional.of(existing));

        var filtered = service.getAllInventory((short) 1, (short) 1, PageRequest.of(0, 10));
        var patched = service.patchInventory(1, TestDataFactory.inventoryRequest((short) 1, (short) 1));

        assertThat(filtered.getContent()).hasSize(1);
        assertThat(patched.getInventoryId()).isEqualTo(1);
        assertThat(patched.getFilmTitle()).isEqualTo("Inception");
    }

    @Test
    void paymentServiceCreatesPaymentsAndSanitizesPageable() {
        PaymentServiceImpl service = new PaymentServiceImpl(paymentRepository, rentalRepository, staffRepository);
        PageData data = TestDataFactory.pageData();
        Rental rental = data.rental();
        rental.setReturnDate(LocalDateTime.now());
        Payment payment = data.payment();
        PaymentRequestDTO request = TestDataFactory.paymentRequest(1, (short) 1);
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

        when(rentalRepository.findById(1)).thenReturn(Optional.of(rental));
        when(staffRepository.findById((short) 1)).thenReturn(Optional.of(data.manager()));
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);
        when(paymentRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(payment)));

        var created = service.createPayment(request);
        var page = service.getAllPayments(PageRequest.of(0, 100, Sort.by("unsafe")));

        assertThat(created.getPaymentId()).isEqualTo(1);
        assertThat(page.getContent()).hasSize(1);
        verify(paymentRepository).findAll(pageableCaptor.capture());
        assertThat(pageableCaptor.getValue().getPageNumber()).isEqualTo(0);
        assertThat(pageableCaptor.getValue().getPageSize()).isEqualTo(10);
        assertThat(pageableCaptor.getValue().getSort().getOrderFor("paymentDate")).isNotNull();
    }

    @Test
    void paymentServiceRejectsUnreturnedRental() {
        PaymentServiceImpl service = new PaymentServiceImpl(paymentRepository, rentalRepository, staffRepository);
        Rental rental = TestDataFactory.pageData().rental();
        rental.setReturnDate(null);
        when(rentalRepository.findById(1)).thenReturn(Optional.of(rental));

        assertThatThrownBy(() -> service.createPayment(TestDataFactory.paymentRequest(1, (short) 1)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Rental not returned yet");
    }

    @Test
    void rentalServiceCreatesRentalAndSanitizesPageable() {
        RentalServiceImpl service = new RentalServiceImpl(rentalRepository, inventoryRepository, customerRepository, staffRepository);
        PageData data = TestDataFactory.pageData();
        Rental rental = data.rental();
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

        when(inventoryRepository.findById(1)).thenReturn(Optional.of(data.inventory()));
        when(customerRepository.findById((short) 1)).thenReturn(Optional.of(data.customer()));
        when(staffRepository.findById((short) 1)).thenReturn(Optional.of(data.manager()));
        when(rentalRepository.existsByInventoryAndReturnDateIsNull(data.inventory())).thenReturn(false);
        when(rentalRepository.save(any(Rental.class))).thenReturn(rental);
        when(rentalRepository.findByCustomer_CustomerId(eq((short) 1), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(rental)));

        var created = service.createRental(TestDataFactory.rentalRequest(1, (short) 1, (short) 1));
        var page = service.getByCustomerId((short) 1, PageRequest.of(0, 60, Sort.by("badField")));

        assertThat(created.getRentalId()).isEqualTo(1);
        assertThat(page.getContent()).hasSize(1);
        verify(rentalRepository).findByCustomer_CustomerId(eq((short) 1), pageableCaptor.capture());
        assertThat(pageableCaptor.getValue().getPageNumber()).isEqualTo(0);
        assertThat(pageableCaptor.getValue().getPageSize()).isEqualTo(10);
        assertThat(pageableCaptor.getValue().getSort().getOrderFor("rentalDate")).isNotNull();
    }

    @Test
    void rentalServiceRejectsAlreadyReturnedRental() {
        RentalServiceImpl service = new RentalServiceImpl(rentalRepository, inventoryRepository, customerRepository, staffRepository);
        Rental rental = TestDataFactory.pageData().rental();
        rental.setReturnDate(LocalDateTime.now());
        when(rentalRepository.findById(1)).thenReturn(Optional.of(rental));

        assertThatThrownBy(() -> service.returnRental(1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Already returned");
    }

    @Test
    void staffServiceCreatesAndSearchesStaff() {
        StaffServiceImpl service = new StaffServiceImpl(staffRepository, storeRepository, addressRepository, cityRepository, countryRepository);
        PageData data = TestDataFactory.pageData();
        StaffRequestDTO request = TestDataFactory.staffRequest();
        Page<Staff> page = new PageImpl<>(List.of(data.manager()));

        when(staffRepository.existsByUsername("mross")).thenReturn(false);
        when(staffRepository.existsByEmail("mike@example.com")).thenReturn(false);
        when(countryRepository.findByCountryIgnoreCase("India")).thenReturn(Optional.of(data.country()));
        when(cityRepository.findByCityIgnoreCaseAndCountry("Bengaluru", data.country())).thenReturn(Optional.of(data.city()));
        when(addressRepository.findByAddressAndCity("221B Baker Street", data.city())).thenReturn(Optional.of(data.address()));
        when(storeRepository.findById((short) 1)).thenReturn(Optional.of(data.store()));
        when(staffRepository.save(any(Staff.class))).thenReturn(data.manager());
        when(staffRepository.findByStaffId((short) 1)).thenReturn(Optional.of(data.manager()));
        when(staffRepository.findByStore_StoreId((short) 1, PageRequest.of(0, 10))).thenReturn(page);

        var created = service.createStaff(request);
        var search = service.searchStaff(null, (short) 1, PageRequest.of(0, 10));

        assertThat(created.getStaffId()).isEqualTo((short) 1);
        assertThat(search.getContent()).hasSize(1);
    }

    @Test
    void staffServicePatchUpdatesStoreAndAddress() {
        StaffServiceImpl service = new StaffServiceImpl(staffRepository, storeRepository, addressRepository, cityRepository, countryRepository);
        PageData data = TestDataFactory.pageData();
        Staff existing = data.manager();
        StaffPatchDTO patch = TestDataFactory.staffPatch();
        Country australia = TestDataFactory.country((short) 2, "Australia");
        City sydney = TestDataFactory.city((short) 2, "Sydney", australia);
        Address newAddress = TestDataFactory.address((short) 2, "42 Wallaby Way", sydney);
        Store newStore = TestDataFactory.store(2, existing, newAddress);

        patch.getAddress().getCity().setCity("Sydney");
        patch.getAddress().getCity().getCountry().setCountry("Australia");
        patch.getAddress().setAddress("42 Wallaby Way");

        when(staffRepository.findById((short) 1)).thenReturn(Optional.of(existing));
        when(storeRepository.findById((short) 2)).thenReturn(Optional.of(newStore));
        when(countryRepository.findByCountryIgnoreCase("Australia")).thenReturn(Optional.of(australia));
        when(cityRepository.findByCityIgnoreCaseAndCountry("Sydney", australia)).thenReturn(Optional.of(sydney));
        when(addressRepository.findByAddressAndCity("42 Wallaby Way", sydney)).thenReturn(Optional.of(newAddress));
        when(staffRepository.save(existing)).thenReturn(existing);

        var response = service.patchStaff((short) 1, patch);

        assertThat(response.getFirstName()).isEqualTo("Harvey");
        assertThat(existing.getStore().getStoreId()).isEqualTo(2);
        assertThat(existing.getAddress().getAddress()).isEqualTo("42 Wallaby Way");
    }

    @Test
    void storeServiceCreatesAndUpdatesStore() {
        StoreServiceImpl service = new StoreServiceImpl(storeRepository, staffRepository, addressService);
        PageData data = TestDataFactory.pageData();
        StoreRequestDTO request = TestDataFactory.storeRequest();
        Store existing = data.store();
        Address newAddress = TestDataFactory.address((short) 2, "42 Wallaby Way", data.city());

        when(addressService.createAndReturnEntity(request.getAddress())).thenReturn(data.address()).thenReturn(newAddress);
        when(staffRepository.findById((short) 1)).thenReturn(Optional.of(data.manager()));
        when(storeRepository.save(any(Store.class))).thenReturn(existing);
        when(storeRepository.findById((short) 1)).thenReturn(Optional.of(existing));

        var created = service.createStore(request);
        var updated = service.updateStore((short) 1, request);

        assertThat(created.getStoreId()).isEqualTo(1);
        assertThat(updated.getStoreId()).isEqualTo(1);
    }
}
