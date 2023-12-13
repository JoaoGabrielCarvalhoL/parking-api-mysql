package br.com.carv.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ReportGenericException extends RuntimeException {
    public ReportGenericException(String message) {
        super(message);
    }
}
