package br.com.carv.parking.controller;

import br.com.carv.parking.exception.response.ResponseException;
import br.com.carv.parking.payload.request.*;
import br.com.carv.parking.payload.response.UserGetResponse;
import br.com.carv.parking.payload.response.UserResponse;
import br.com.carv.parking.payload.response.VacancyGetResponse;
import br.com.carv.parking.payload.response.VacancyResponse;
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
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Vacancy Controller", description = "Endpoint for managing vacancy of API.")
@PreAuthorize("hasRole('ADMIN')")
public interface VacancyController {


    @Operation(summary = "Create Vacancy.", description = "Request to create a new vacancy.",
    security = @SecurityRequirement(name = "security"))
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Created.", content =
            { @Content(schema = @Schema(implementation = VacancyResponse.class))}),
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
    ResponseEntity<EntityModel<VacancyResponse>> save(@RequestBody @Valid VacancyPostRequest vacancyPostRequest);


    @Operation(summary = "Find Vacancy By Id.", description = "Request to find vacancy by id.",
            security = @SecurityRequirement(name = "security"))
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK.", content =
            { @Content(schema = @Schema(implementation = VacancyGetResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "403", description = "Forbidden."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")})
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN') OR (hasRole('USER') AND #id == authentication.principal.id)")
    ResponseEntity<VacancyGetResponse> findById(@PathVariable("id") UUID id);

    @Operation(summary = "Find All vacancies.", description = "Request to find all vacancies on system.",
            security = @SecurityRequirement(name = "security"))
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK.", content =
            { @Content(schema = @Schema(implementation = VacancyGetResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "403", description = "Forbidden."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")})
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<VacancyGetResponse>> findAll(Pageable pageable);


    @Operation(summary = "Delete Vacancy.", description = "Request to delete vacancy of system.",
            security = @SecurityRequirement(name = "security"))
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "No Content."),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "403", description = "Forbidden."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> delete(@PathVariable("id") UUID id);


    @Operation(summary = "Update Vacancy.", description = "Request to update vacancy of system.",
            security = @SecurityRequirement(name = "security"))
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "No Content."),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "403", description = "Forbidden."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")})
    @PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> update(@RequestBody @Valid VacancyPutRequest vacancyPutRequest);

}
