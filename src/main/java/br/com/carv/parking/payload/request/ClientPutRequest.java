package br.com.carv.parking.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.util.UUID;

public record ClientPutRequest(
        @NotNull(message = "The field id cannot be null or empty!")
        UUID id,
        @NotBlank(message = "The field name cannot be empty!") @Size(max = 100, min = 5)
        String name,
        @NotBlank(message = "The field certificatePerson cannot be empty!")
        @Size(min = 11, max = 11)
        @CPF
        String certificatePerson
) implements Serializable {
}
