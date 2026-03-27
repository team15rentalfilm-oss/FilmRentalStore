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
                .contains("/api/categories")
                .contains("/api/customers")
                .contains("/api/film-actor")
                .contains("/api/films")
                .contains("/api/v1/film-texts")
                .contains("/api/v1/inventories")
                .contains("/api/languages")
                .contains("/api/payments")
                .contains("/api/rentals")
                .contains("/api/v1/staff")
                .contains("/api/stores");
    }
}
