package br.com.carv.parking.controller;

import br.com.carv.parking.jwt.response.TokenResponse;
import br.com.carv.parking.payload.request.LoginPostRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Auth Controller", description = "Endpoint to authenticate/create user")
public interface AuthController {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginPostRequest loginPostRequest);


}