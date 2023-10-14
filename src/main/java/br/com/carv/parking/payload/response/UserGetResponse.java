package br.com.carv.parking.payload.response;

import br.com.carv.parking.enumerations.Role;

import java.io.Serializable;
import java.util.UUID;

public record UserGetResponse(
        UUID id,
        String name,
        String email,
        String username,
        Role role
) implements Serializable {
}
