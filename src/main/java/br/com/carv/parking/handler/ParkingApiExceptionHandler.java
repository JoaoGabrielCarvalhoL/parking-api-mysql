package br.com.carv.parking.handler;

import br.com.carv.parking.exception.*;
import br.com.carv.parking.exception.response.ErrorMessage;
import br.com.carv.parking.exception.response.ResponseException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ParkingApiExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ResponseException> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ResponseException exceptionResponse =
                new ResponseException("Bad Request", HttpStatus.BAD_REQUEST.value(),
                        exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<ResponseException>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAlreadyUsedException.class)
    public final ResponseEntity<ResponseException> handleResourceAlreadyUsedException(ResourceAlreadyUsedException exception) {
        ResponseException exceptionResponse =
                new ResponseException("Bad Request", HttpStatus.BAD_REQUEST.value(),
                        exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<ResponseException>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceUnavailableException.class)
    public final ResponseEntity<ResponseException> handleResourceUnavailableException(ResourceUnavailableException exception) {
        ResponseException exceptionResponse =
                new ResponseException("Bad Request", HttpStatus.BAD_REQUEST.value(),
                        exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<ResponseException>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordMissMatchException.class)
    public final ResponseEntity<ResponseException> handlePasswordMissMatchException(PasswordMissMatchException exception) {
        ResponseException exceptionResponse =
                new ResponseException("Bad Request", HttpStatus.BAD_REQUEST.value(),
                        exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<ResponseException>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest httpServletRequest,
                                                               BindingResult bindingResult) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(httpServletRequest, HttpStatus.BAD_REQUEST, ex.getMessage(), bindingResult));

    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseException> handleAccessDeniedException(AccessDeniedException ex,
                                                                         HttpServletRequest httpServletRequest) {
        ResponseException response = new ResponseException("Forbidden", HttpStatus.FORBIDDEN.value(),
                ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<ResponseException>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ReportGenericException.class)
    public final ResponseEntity<ResponseException> handleReportGenericException(ReportGenericException exception) {
        ResponseException exceptionResponse =
                new ResponseException("UNPROCESSABLE ENTITY", HttpStatus.UNPROCESSABLE_ENTITY.value(),
                        exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<ResponseException>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
