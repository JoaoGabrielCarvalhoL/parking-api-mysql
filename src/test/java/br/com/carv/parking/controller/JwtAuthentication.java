package br.com.carv.parking.controller;

import br.com.carv.parking.jwt.response.TokenResponse;
import br.com.carv.parking.payload.request.LoginPostRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Objects;
import java.util.function.Consumer;

public class JwtAuthentication {

    public static Consumer<HttpHeaders> getHeaderAuthorization(WebTestClient webTestClient, String username, String password) {
        String token = Objects.requireNonNull(webTestClient.post().uri("api/v1/auth")
                .bodyValue(new LoginPostRequest(username, password))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(TokenResponse.class)
                .returnResult()
                .getResponseBody()).token();

        return httpHeaders -> httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    }
}
