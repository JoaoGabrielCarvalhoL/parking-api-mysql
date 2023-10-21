package br.com.carv.parking.controller;

import br.com.carv.parking.exception.response.ResponseException;
import br.com.carv.parking.payload.request.*;
import br.com.carv.parking.payload.response.ClientGetResponse;
import br.com.carv.parking.payload.response.ClientResponse;
import br.com.carv.parking.payload.response.UserGetResponse;
import br.com.carv.parking.payload.response.UserResponse;
import br.com.carv.parking.security.UserSecurity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Client Controller", description = "Endpoint for managing clients from API.")
@PreAuthorize("hasRole('USER')")
public interface ClientController {

    @Operation(summary = "Create Client.", description = "Request to create a new client.",
    security = @SecurityRequirement(name = "security"))
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Created.", content =
            { @Content(schema = @Schema(implementation = ClientResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseException.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseException.class))),

            @ApiResponse(responseCode = "403", description = "Forbidden.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseException.class))),

            @ApiResponse(responseCode = "500", description = "Internal Server Error.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseException.class)))})
    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<EntityModel<ClientResponse>> save(@AuthenticationPrincipal UserSecurity userSecurity,
                                                     @RequestBody @Valid ClientPostRequest clientPostRequest);


    @Operation(summary = "Find Client By Id.", description = "Request to find client by id.",
            security = @SecurityRequirement(name = "security"))
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK.", content =
            { @Content(schema = @Schema(implementation = UserGetResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "403", description = "Forbidden."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")})
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN') OR (hasRole('USER') AND #id == authentication.principal.id)")
    ResponseEntity<ClientGetResponse> findById(@PathVariable("id") UUID id);


    @Operation(summary = "Find All Clients.", description = "Request to find all clients on system.",
            security = @SecurityRequirement(name = "security"))
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK.", content =
            { @Content(schema = @Schema(implementation = UserGetResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "403", description = "Forbidden."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")})
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Page<ClientGetResponse>> findAll(Pageable pageable);


    @Operation(summary = "Delete Client.", description = "Request to delete client of system.",
            security = @SecurityRequirement(name = "security"))
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "No Content."),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "403", description = "Forbidden."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Void> delete(@PathVariable("id") UUID id);


    @Operation(summary = "Update Client.", description = "Request to update client of system.",
            security = @SecurityRequirement(name = "security"))
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "No Content."),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "403", description = "Forbidden."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")})
    @PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> update(@AuthenticationPrincipal UserSecurity userSecurity,
                                @RequestBody @Valid ClientPutRequest clientPutRequest);

}
