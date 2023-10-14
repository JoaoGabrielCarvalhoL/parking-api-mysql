package br.com.carv.parking.exception.response;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ResponseException(
        String title,
        Integer status,
        String details,
        LocalDateTime timestamp
) implements Serializable {
}
