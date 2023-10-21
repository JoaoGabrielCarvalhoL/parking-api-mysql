package br.com.carv.parking.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

public record ClientPostRequest(

        @NotBlank(message = "The field name cannot be empty!") @Size(max = 100, min = 5)
        String name,
        @NotBlank(message = "The field certificatePerson cannot be empty!")
        @Size(min = 11, max = 11)
        @CPF
        String certificatePerson
) implements Serializable {
}
