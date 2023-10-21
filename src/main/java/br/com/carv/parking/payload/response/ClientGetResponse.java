package br.com.carv.parking.payload.response;

import java.io.Serializable;
import java.util.UUID;

public record ClientGetResponse(
        UUID id,
        String name,
        String certificatePerson
) implements Serializable {
}
