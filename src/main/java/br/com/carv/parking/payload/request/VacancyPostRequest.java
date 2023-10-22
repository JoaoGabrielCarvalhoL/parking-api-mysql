package br.com.carv.parking.payload.request;

import br.com.carv.parking.enumerations.VacancyStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record VacancyPostRequest(
        @NotBlank(message = "The field code cannot be empty!") @Size(min = 4, max = 5)
        String code,
        @NotNull(message = "The field status cannot be empty!")
        VacancyStatus status
) implements Serializable {
}
