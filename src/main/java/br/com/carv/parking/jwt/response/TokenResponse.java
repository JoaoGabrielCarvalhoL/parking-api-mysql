package br.com.carv.parking.jwt.response;

import java.io.Serializable;

public record TokenResponse(
        String token,
        String type
) implements Serializable {
}
