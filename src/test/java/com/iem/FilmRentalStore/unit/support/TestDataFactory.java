package com.iem.FilmRentalStore.unit.support;

import com.iem.FilmRentalStore.dto.actor.ActorRequestDTO;
import com.iem.FilmRentalStore.dto.address.AddressRequestDTO;
import com.iem.FilmRentalStore.dto.category.CategoryRequestDTO;
import com.iem.FilmRentalStore.dto.city.CityPatchDTO;
import com.iem.FilmRentalStore.dto.city.CityRequestDTO;
import com.iem.FilmRentalStore.dto.country.CountryRequestDTO;
import com.iem.FilmRentalStore.dto.customer.CustomerPatchDTO;
import com.iem.FilmRentalStore.dto.customer.CustomerRequestDTO;
import com.iem.FilmRentalStore.dto.film.FilmPatchDTO;
import com.iem.FilmRentalStore.dto.film.FilmRequestDTO;
import com.iem.FilmRentalStore.dto.inventory.InventoryRequestDTO;
import com.iem.FilmRentalStore.dto.language.LanguageRequestDTO;
import com.iem.FilmRentalStore.dto.payment.PaymentPatchDTO;
import com.iem.FilmRentalStore.dto.payment.PaymentRequestDTO;
import com.iem.FilmRentalStore.dto.rental.RentalPatchDTO;
import com.iem.FilmRentalStore.dto.rental.RentalRequestDTO;
import com.iem.FilmRentalStore.dto.staff.StaffPatchDTO;
import com.iem.FilmRentalStore.dto.staff.StaffRequestDTO;
import com.iem.FilmRentalStore.dto.store.StoreRequestDTO;
import com.iem.FilmRentalStore.entity.Actor;
import com.iem.FilmRentalStore.entity.Address;
import com.iem.FilmRentalStore.entity.Category;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class TestDataFactory {

    private TestDataFactory() {
    }

    public static ActorRequestDTO actorRequest(String firstName, String lastName) {
        ActorRequestDTO dto = new ActorRequestDTO();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        return dto;
    }

    public static Actor actor(short id, String firstName, String lastName) {
        Actor actor = new Actor();
        actor.setActorId(id);
        actor.setFirstName(firstName);
        actor.setLastName(lastName);
        return actor;
    }

    public static CategoryRequestDTO categoryRequest(String name) {
        CategoryRequestDTO dto = new CategoryRequestDTO();
        dto.setName(name);
        return dto;
    }

    public static Category category(byte id, String name) {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        category.setLastUpdate(LocalDateTime.now());
        return category;
    }

    public static CountryRequestDTO countryRequest(String name) {
        CountryRequestDTO dto = new CountryRequestDTO();
        dto.setCountry(name);
        return dto;
    }

    public static Country country(short id, String name) {
        Country country = new Country();
        country.setCountryId(id);
        country.setCountry(name);
        country.setLastUpdate(LocalDateTime.now());
        return country;
    }

    public static CityRequestDTO cityRequest(String cityName, String countryName) {
        CityRequestDTO dto = new CityRequestDTO();
        dto.setCity(cityName);
        dto.setCountry(countryRequest(countryName));
        return dto;
    }

    public static CityPatchDTO cityPatch(String cityName, String countryName) {
        CityPatchDTO dto = new CityPatchDTO();
        dto.setCity(cityName);
        dto.setCountry(countryName);
        return dto;
    }

    public static City city(short id, String cityName, Country country) {
        City city = new City();
        city.setCityId(id);
        city.setCity(cityName);
        city.setCountry(country);
        city.setLastUpdate(LocalDateTime.now());
        return city;
    }

    public static AddressRequestDTO addressRequest() {
        AddressRequestDTO dto = new AddressRequestDTO();
        dto.setAddress("221B Baker Street");
        dto.setAddress2("Near Station");
        dto.setDistrict("Central");
        dto.setPostalCode("560001");
        dto.setPhone("9999999999");
        dto.setCity(cityRequest("Bengaluru", "India"));
        return dto;
    }

    public static Address address(short id, String line1, City city) {
        Address address = new Address();
        address.setAddressId(id);
        address.setAddress(line1);
        address.setAddress2("Near Station");
        address.setDistrict("Central");
        address.setPostalCode("560001");
        address.setPhone("9999999999");
        address.setCity(city);
        address.setLastUpdate(LocalDateTime.now());
        return address;
    }

    public static LanguageRequestDTO languageRequest(String name) {
        LanguageRequestDTO dto = new LanguageRequestDTO();
        dto.setName(name);
        return dto;
    }

    public static Language language(int id, String name) {
        Language language = new Language();
        language.setLanguageId(id);
        language.setName(name);
        language.setLastUpdate(LocalDateTime.now());
        return language;
    }

    public static FilmRequestDTO filmRequest() {
        FilmRequestDTO dto = new FilmRequestDTO();
        dto.setTitle("Inception");
        dto.setDescription("Dream heist");
        dto.setReleaseYear(2010);
        dto.setLanguage("English");
        dto.setCategories(Set.of("Sci-Fi"));
        dto.setActors(Set.of("Leonardo DiCaprio"));
        dto.setRentalDuration(7);
        dto.setRentalRate(BigDecimal.valueOf(4.99));
        dto.setLength(148);
        dto.setReplacementCost(BigDecimal.valueOf(19.99));
        dto.setRating("PG-13");
        dto.setSpecialFeatures(Set.of("Trailers"));
        return dto;
    }

    public static FilmPatchDTO filmPatch() {
        FilmPatchDTO dto = new FilmPatchDTO();
        dto.setTitle("Updated Title");
        dto.setDescription("Updated Description");
        dto.setReleaseYear(2011);
        dto.setRating("R");
        return dto;
    }

    public static Film film(short id, String title, Language language, Set<Category> categories, Set<Actor> actors) {
        Film film = new Film();
        film.setFilmId(id);
        film.setTitle(title);
        film.setDescription("Description");
        film.setReleaseYear(2010);
        film.setLanguage(language);
        film.setRentalDuration(7);
        film.setRentalRate(BigDecimal.valueOf(4.99));
        film.setLength(120);
        film.setReplacementCost(BigDecimal.valueOf(19.99));
        film.setRating("PG");
        film.setSpecialFeatures("Trailers");
        film.setCategories(new HashSet<>(categories));
        film.setActors(new HashSet<>(actors));
        film.setLastUpdate(LocalDateTime.now());
        return film;
    }

    public static InventoryRequestDTO inventoryRequest(short filmId, short storeId) {
        InventoryRequestDTO dto = new InventoryRequestDTO();
        dto.setFilmId(filmId);
        dto.setStoreId(storeId);
        return dto;
    }

    public static Inventory inventory(int id, Film film, Store store) {
        Inventory inventory = new Inventory();
        inventory.setInventoryId(id);
        inventory.setFilm(film);
        inventory.setStore(store);
        inventory.setLastUpdate(LocalDateTime.now());
        return inventory;
    }

    public static CustomerRequestDTO customerRequest() {
        CustomerRequestDTO dto = new CustomerRequestDTO();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setEmail("john@example.com");
        dto.setAddress(addressRequest());
        dto.setStoreId((short) 1);
        dto.setActive(true);
        return dto;
    }

    public static CustomerPatchDTO customerPatch() {
        CustomerPatchDTO dto = new CustomerPatchDTO();
        dto.setFirstName("Jane");
        dto.setStoreId((short) 2);
        dto.setAddress("42 Wallaby Way");
        dto.setAddress2("Suite 3");
        dto.setDistrict("North");
        dto.setPostalCode("400001");
        dto.setPhone("8888888888");
        dto.setCity("Sydney");
        dto.setCountry("Australia");
        return dto;
    }

    public static Customer customer(short id, Address address, Store store) {
        Customer customer = new Customer();
        customer.setCustomerId(id);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john@example.com");
        customer.setAddress(address);
        customer.setStore(store);
        customer.setActive(true);
        customer.setCreateDate(LocalDateTime.now());
        customer.setLastUpdate(LocalDateTime.now());
        return customer;
    }

    public static StaffRequestDTO staffRequest() {
        StaffRequestDTO dto = new StaffRequestDTO();
        dto.setFirstName("Mike");
        dto.setLastName("Ross");
        dto.setEmail("mike@example.com");
        dto.setUsername("mross");
        dto.setPassword("secret1");
        dto.setAddress(addressRequest());
        dto.setStoreId((short) 1);
        dto.setActive(true);
        dto.setIsManager(false);
        return dto;
    }

    public static StaffPatchDTO staffPatch() {
        return StaffPatchDTO.builder()
                .firstName("Harvey")
                .username("hspecter")
                .storeId((short) 2)
                .address(addressRequest())
                .build();
    }

    public static Staff staff(short id, Address address, Store store) {
        Staff staff = new Staff();
        staff.setStaffId(id);
        staff.setFirstName("Manager");
        staff.setLastName("One");
        staff.setEmail("manager@example.com");
        staff.setAddress(address);
        staff.setStore(store);
        staff.setUsername("manager1");
        staff.setPassword("secret");
        staff.setActive(true);
        staff.setLastUpdate(LocalDateTime.now());
        return staff;
    }

    public static StoreRequestDTO storeRequest() {
        StoreRequestDTO dto = new StoreRequestDTO();
        dto.setManagerStaffId((short) 1);
        dto.setAddress(addressRequest());
        return dto;
    }

    public static Store store(int id, Staff manager, Address address) {
        Store store = new Store();
        store.setStoreId(id);
        store.setManagerStaff(manager);
        store.setAddress(address);
        store.setLastUpdate(LocalDateTime.now());
        return store;
    }

    public static RentalRequestDTO rentalRequest(int inventoryId, short customerId, short staffId) {
        RentalRequestDTO dto = new RentalRequestDTO();
        dto.setInventoryId(inventoryId);
        dto.setCustomerId(customerId);
        dto.setStaffId(staffId);
        return dto;
    }

    public static RentalPatchDTO rentalPatch(short staffId) {
        RentalPatchDTO dto = new RentalPatchDTO();
        dto.setReturnDate(LocalDateTime.now());
        dto.setStaffId(staffId);
        return dto;
    }

    public static Rental rental(int id, Inventory inventory, Customer customer, Staff staff) {
        Rental rental = new Rental();
        rental.setRentalId(id);
        rental.setInventory(inventory);
        rental.setCustomer(customer);
        rental.setStaff(staff);
        rental.setRentalDate(LocalDateTime.now().minusDays(2));
        rental.setLastUpdate(LocalDateTime.now());
        return rental;
    }

    public static PaymentRequestDTO paymentRequest(int rentalId, short staffId) {
        PaymentRequestDTO dto = new PaymentRequestDTO();
        dto.setRentalId(rentalId);
        dto.setStaffId(staffId);
        dto.setAmount(BigDecimal.valueOf(10.00));
        return dto;
    }

    public static PaymentPatchDTO paymentPatch(short staffId) {
        PaymentPatchDTO dto = new PaymentPatchDTO();
        dto.setAmount(BigDecimal.valueOf(15.00));
        dto.setStaffId(staffId);
        return dto;
    }

    public static Payment payment(int id, Rental rental, Staff staff) {
        Payment payment = new Payment();
        payment.setPaymentId(id);
        payment.setRental(rental);
        payment.setCustomer(rental.getCustomer());
        payment.setStaff(staff);
        payment.setAmount(BigDecimal.valueOf(10.00));
        payment.setPaymentDate(LocalDateTime.now());
        payment.setLastUpdate(LocalDateTime.now());
        return payment;
    }

    public static PageData pageData() {
        Country country = country((short) 1, "India");
        City city = city((short) 1, "Bengaluru", country);
        Address address = address((short) 1, "221B Baker Street", city);
        Store store = store(1, null, address);
        Staff manager = staff((short) 1, address, store);
        store.setManagerStaff(manager);
        Customer customer = customer((short) 1, address, store);
        Language language = language(1, "English");
        Category category = category((byte) 1, "Sci-Fi");
        Actor actor = actor((short) 1, "Leonardo", "DiCaprio");
        Film film = film((short) 1, "Inception", language, Set.of(category), Set.of(actor));
        Inventory inventory = inventory(1, film, store);
        Rental rental = rental(1, inventory, customer, manager);
        Payment payment = payment(1, rental, manager);
        return new PageData(country, city, address, store, manager, customer, language, category, actor, film, inventory, rental, payment);
    }

    public record PageData(
            Country country,
            City city,
            Address address,
            Store store,
            Staff manager,
            Customer customer,
            Language language,
            Category category,
            Actor actor,
            Film film,
            Inventory inventory,
            Rental rental,
            Payment payment
    ) {
    }
}
