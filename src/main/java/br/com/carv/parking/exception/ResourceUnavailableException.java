package br.com.carv.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceUnavailableException extends RuntimeException {
    public ResourceUnavailableException(String message) {
        super(message);
    }
}
