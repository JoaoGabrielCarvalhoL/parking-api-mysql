package br.com.carv.parking.payload.request;

import java.io.Serializable;

public record PasswordResetPostRequest(
        String oldPassword,
        String newPassword,
        String confirmedPassword
) implements Serializable {
}
