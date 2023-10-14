package br.com.carv.parking.controller;

import br.com.carv.parking.payload.request.PasswordResetPostRequest;
import br.com.carv.parking.payload.request.UserPostRequest;
import br.com.carv.parking.payload.request.UserPutRequest;
import br.com.carv.parking.payload.response.UserGetResponse;
import br.com.carv.parking.payload.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "User Controller", description = "Endpoint for managing API users.")
public interface UserSystemController {


    @Operation(summary = "Create User.", description = "Request to create a new user.")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Created.", content =
            { @Content(schema = @Schema(implementation = UserResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "403", description = "Forbidden."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")})
    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<EntityModel<UserResponse>> save(@RequestBody @Valid UserPostRequest userPostRequest,
                                                   HttpServletRequest httpServletRequest);


    @Operation(summary = "Find User By Id.", description = "Request to find user by id.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK.", content =
            { @Content(schema = @Schema(implementation = UserGetResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "403", description = "Forbidden."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")})
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserGetResponse> findById(@PathVariable("id") UUID id);


    @Operation(summary = "Find All Users.", description = "Request to find all users on system.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK.", content =
            { @Content(schema = @Schema(implementation = UserGetResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "403", description = "Forbidden."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")})
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<UserGetResponse>> findAll(Pageable pageable);


    @Operation(summary = "Delete User.", description = "Request to delete user of system.")
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "No Content."),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "403", description = "Forbidden."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> delete(@PathVariable("id") UUID id);


    @Operation(summary = "Update User.", description = "Request to update user of system.")
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "No Content."),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "403", description = "Forbidden."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")})
    @PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> update(@RequestBody @Valid UserPutRequest userPutRequest);


    @Operation(summary = "Change Password.", description = "Request to change password of a user of system.")
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "No Content."),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "403", description = "Forbidden."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")})
    @PatchMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> changePassword(@PathVariable("id") UUID id, @RequestBody @Valid PasswordResetPostRequest
            passwordResetPostRequest);
}
