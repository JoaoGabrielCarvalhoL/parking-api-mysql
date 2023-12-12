package br.com.carv.parking.controller;

import br.com.carv.parking.payload.request.VacancyClientPostRequest;
import br.com.carv.parking.payload.response.VacancyClientGetResponse;
import br.com.carv.parking.repository.projection.VacancyClientProjection;
import br.com.carv.parking.security.UserSecurity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Parking Controller", description = "Endpoint for managing parking.")
@PreAuthorize("hasRole('ADMIN')")
public interface ParkingController {

    @Operation(summary = "Check in.", description = "Request to realize check-in on parking.",
            security = @SecurityRequirement(name = "security"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK.",
                    headers = @Header(name = HttpHeaders.LOCATION, description = "URL access of resource"),
                    content = {@Content(mediaType = "application/json;charset=UTF-8", schema = @Schema(implementation = VacancyClientGetResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "403", description = "Forbidden."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")})

    @PostMapping(value = "/check-in", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<VacancyClientGetResponse> checkIn(@RequestBody @Valid
                                                     VacancyClientPostRequest vacancyClientPostRequest);


    @Operation(summary = "Get Receipt.", description = "Request to get receipt.",
            security = @SecurityRequirement(name = "security"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK.",
                    content = {@Content(mediaType = "application/json;charset=UTF-8", schema = @Schema(implementation = VacancyClientGetResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "403", description = "Forbidden."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")})
    @GetMapping(value = "/check-in/{receipt}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    ResponseEntity<VacancyClientGetResponse> getByReceipt(@PathVariable("receipt") String receipt);


    @Operation(summary = "Check Out.", description = "Request to realize check-out.",
            security = @SecurityRequirement(name = "security"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK.",
                    content = {@Content(mediaType = "application/json;charset=UTF-8", schema = @Schema(implementation = VacancyClientGetResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "403", description = "Forbidden."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")})
    @PutMapping(value = "/check-out/{receipt}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<VacancyClientGetResponse> checkOut(@PathVariable("receipt") String receipt);


    @Operation(summary = "Get all.", description = "Request to retrieve all parking by certificate person.",
            security = @SecurityRequirement(name = "security"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK.",
                    content = {@Content(mediaType = "application/json;charset=UTF-8", schema = @Schema(implementation = VacancyClientGetResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "403", description = "Forbidden."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")})
    @GetMapping(value = "/all/{certificate}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<VacancyClientProjection>> getAllParking(@PageableDefault(size = 10, page = 0,
            direction = Sort.Direction.ASC,  sort = "entryDate") Pageable pageable, @PathVariable("certificate") String certificate);

    @Operation(summary = "Get all parking per client.", description = "Request to retrieve all parking by client.",
            security = @SecurityRequirement(name = "security"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK.",
                    content = {@Content(mediaType = "application/json;charset=UTF-8", schema = @Schema(implementation = VacancyClientGetResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "403", description = "Forbidden."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")})
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<Page<VacancyClientGetResponse>> getAllParkingPerClient(@PageableDefault(size = 10, page = 0,
            direction = Sort.Direction.ASC,  sort = "entryDate") Pageable pageable, @AuthenticationPrincipal UserSecurity userSecurity);
}
