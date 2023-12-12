package br.com.carv.parking.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

@JsonPropertyOrder({"certificatePersonClient", "license", "model", "brand", "color"})
public record ParkingPostRequest(
        @NotBlank(message = "The field license cannot be empty.")
        @Size(max = 8, min = 8)
                @Pattern(regexp = "[A-Z]{3}-[0-9]{4}", message = "The vehicle license plate must follow the standard 'XXX-0000'")
        String license,
        @NotBlank(message = "The field model cannot be empty.")
        String model,
        @NotBlank(message = "The field brand cannot be empty.")
        String brand,
        @NotBlank(message = "The field color cannot be empty.")
        String color,
        @CPF
        @JsonProperty("CPF")
        @NotBlank(message = "The field CPF cannot be empty")
        String certificatePersonClient
) implements Serializable {
}
