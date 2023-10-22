package br.com.carv.parking.payload.response;

import br.com.carv.parking.enumerations.VacancyStatus;

import java.io.Serializable;
import java.util.UUID;

public record VacancyGetResponse(
        UUID id,
        String code,
        VacancyStatus status
) implements Serializable {
}
