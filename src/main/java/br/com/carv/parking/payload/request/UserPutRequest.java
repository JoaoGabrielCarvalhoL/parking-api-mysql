package br.com.carv.parking.payload.request;

import br.com.carv.parking.enumerations.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.UUID;

public record UserPutRequest(

        @NotNull(message = "The field id cannot be empty!")
        UUID id,
        @NotBlank(message = "The field name cannot be empty!") @Size(min = 5, max = 200)
        String name,
        @NotBlank(message = "The field email cannot be empty!")
        @Email(regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
        String email,

        @NotBlank(message = "The field username cannot be empty!") @Size(min = 5, max = 200)
        String username,

        @NotNull(message = "The field role cannot be empty!")
        Role role,

        @JsonProperty("password")
        @NotBlank(message = "The field password cannot be empty!")
        String passwordHash
) implements Serializable {
}
