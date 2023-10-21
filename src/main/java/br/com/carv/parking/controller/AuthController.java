package br.com.carv.parking.controller;

import br.com.carv.parking.jwt.response.TokenResponse;
import br.com.carv.parking.payload.request.LoginPostRequest;
import br.com.carv.parking.payload.response.UserGetResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Auth Controller", description = "Endpoint to authenticate user")
public interface AuthController {

    @Operation(summary = "Authentication.", description = "Request to authenticate user and generate JWT Token")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK.", content =
            { @Content(schema = @Schema(implementation = TokenResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginPostRequest loginPostRequest);


}