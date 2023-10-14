package br.com.carv.parking.controller;

import br.com.carv.parking.enumerations.Role;
import br.com.carv.parking.exception.response.ResponseException;
import br.com.carv.parking.payload.request.UserPostRequest;
import br.com.carv.parking.payload.response.UserGetResponse;
import br.com.carv.parking.payload.response.UserResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.UUID;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql(scripts = "sql/user/user_system_insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class UserSystemIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Order(1)
    @Test
    @DisplayName("Given a user, when username and password is valid, then return persisted user from database.")
    void testGivenUser_whenUsernameAndPasswordValid_thenReturnPersistedUserWithHttpStatusCreated() {
        UserResponse response = webTestClient.post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(header -> header.setBasicAuth("admin", "admin"))
                .bodyValue(new UserPostRequest("João Gabriel Carvalho",
                        "27.joaogabriel@gmail.com", "joaogabriel", Role.ROLE_ADMIN, "admin"))
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(UserResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getId()).isNotNull();
        Assertions.assertThat(response.getUsername()).isEqualTo("joaogabriel");
        Assertions.assertThat(response.getEmail()).isEqualTo("27.joaogabriel@gmail.com");
    }

    @Order(2)
    @Test
    @DisplayName("Given a user, when username is not valid, then return error response.")
    void testGivenUser_whenUsernameIsNotValid_thenReturnExceptionAndHttpStatusBadRequest() {
        ResponseException responseBody = webTestClient.post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(header -> header.setBasicAuth("admin", "admin"))
                .bodyValue(new UserPostRequest("João Gabriel Carvalho",
                        "27.joaogabriel@gmail.com", "joaogabriel1", Role.ROLE_ADMIN, "admin"))
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBody(ResponseException.class)
                .returnResult().getResponseBody();

        assert responseBody != null;
        Assertions.assertThat(responseBody.status()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Order(3)
    @Test
    @DisplayName("Given a user, when email is not valid, then return error response.")
    void testGivenUser_whenEmailIsNotValid_thenReturnExceptionAndHttpStatusBadRequest() {
        ResponseException responseBody = webTestClient.post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(header -> header.setBasicAuth("admin", "admin"))
                .bodyValue(new UserPostRequest("João Gabriel Carvalho",
                        "27.joaogabriel@gmail.com", "joaogabriel1", Role.ROLE_ADMIN, "admin"))
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBody(ResponseException.class)
                .returnResult().getResponseBody();

        assert responseBody != null;
        Assertions.assertThat(responseBody.status()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Order(4)
    @Test
    @DisplayName("Given a id, when find by id method, then return user response.")
    void testGivenExistingId_whenFindUserById_thenReturnUserAndHttpStatusOK() {
        UserResponse response = webTestClient.post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(header -> header.setBasicAuth("admin", "admin"))
                .bodyValue(new UserPostRequest("João Gabriel Carvalho",
                        "27.testjoaogabriel@gmail.com", "joaogabrielcarv", Role.ROLE_ADMIN, "admin"))
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(UserResponse.class)
                .returnResult().getResponseBody();


        assert response != null;
        String id = response.getId().toString();
        UserGetResponse responseBody = webTestClient.get()
                .uri("/api/v1/users/" + id )
                .accept(MediaType.APPLICATION_JSON)
                .headers(header -> header.setBasicAuth("admin", "admin"))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(UserGetResponse.class)
                .returnResult().getResponseBody();

        assert responseBody != null;
        Assertions.assertThat(responseBody.id()).isEqualTo(response.getId());
    }

    @Order(5)
    @Test
    @DisplayName("Given a invalid id, when find by id method, then return throw exception response.")
    void testGivenInvalidId_whenFindUserById_thenThrowExceptionAndHttpStatusBadRequest() {

        String id = UUID.randomUUID().toString();
        ResponseException responseBody = webTestClient.get()
                .uri("/api/v1/users/" + id)
                .headers(header -> header.setBasicAuth("admin", "admin"))
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBody(ResponseException.class)
                .returnResult().getResponseBody();

        assert responseBody != null;
        Assertions.assertThat(responseBody.status()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Order(6)
    @Test
    @DisplayName("Given a user list, when find all method, then return a list of user response.")
    void testGivenUserList_whenFindAll_thenReturnUserListAndHttpStatusOK() {
        UserResponse response = webTestClient.post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(header -> header.setBasicAuth("admin", "admin"))
                .bodyValue(new UserPostRequest("João Gabriel Carvalho",
                        "277.testjoaogabriel@gmail.com", "joaogabrielcarva", Role.ROLE_ADMIN, "admin"))
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(UserResponse.class)
                .returnResult().getResponseBody();

        Pageable pageable = PageRequest.of(0, 20, Sort.Direction.ASC, "name");
        UserGetResponse responseBody = webTestClient.get()
                .uri("/api/v1/users?page=0&size=20&sort=ASC")
                .accept(MediaType.APPLICATION_JSON)
                .headers(header -> header.setBasicAuth("admin", "admin"))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(UserGetResponse.class)
                .returnResult().getResponseBody();

        assert responseBody != null;
        Assertions.assertThat(responseBody).isNotNull();
    }
}

