package com.iem.FilmRentalStore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SwaggerApiDocsTest {

    @LocalServerPort
    private int port;

    @Test
    void apiDocsLoadsAndIncludesAllRestControllers() {
        ResponseEntity<String> response = RestClient.create()
                .get()
                .uri("http://localhost:" + port + "/v3/api-docs")
                .retrieve()
                .toEntity(String.class);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody())
                .contains("/api/actors")
                .contains("/api/addresses")
                .contains("/api/categories")
                .contains("/api/cities")
                .contains("/api/countries")
                .contains("/api/customers")
                .contains("/api/films")
                .contains("/api/inventory")
                .contains("/api/languages")
                .contains("/api/payments")
                .contains("/api/rentals")
                .contains("/api/staff")
                .contains("/api/stores");
    }
}
