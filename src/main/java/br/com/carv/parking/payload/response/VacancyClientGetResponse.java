package br.com.carv.parking.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"certificatePersonClient", "code","license", "model", "brand",
        "color", "receipt", "value", "discount", "entryDate", "departureDate"})
public record VacancyClientGetResponse(
        String code,
        String license,
        String model,
        String brand,
        String color,
        @JsonProperty("CPF")
        String certificatePersonClient,
        String receipt,
        LocalDateTime entryDate,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        LocalDateTime departureDate,
        BigDecimal value,
        BigDecimal discount
) implements Serializable {
}
