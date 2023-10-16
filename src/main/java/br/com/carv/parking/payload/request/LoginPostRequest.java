package br.com.carv.parking.payload.request;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record LoginPostRequest(
        @NotBlank(message = "The field username cannot be empty.")
        String username,
        @NotBlank(message = "The field password cannot be empty.")
        String password
) implements Serializable {
}
