package br.com.carv.parking.exception.response;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorMessage implements Serializable {

    private String path;
    private String method;
    private Integer status;
    private String statusTitle;
    private String statusText;
    private String message;

    private Map<String, String> errors;

    public ErrorMessage() {
        this.errors = new HashMap<>();
    }

    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message) {
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusTitle = status.name();
        this.statusText = status.getReasonPhrase();
        this.message = message;
        this.errors = new HashMap<>();
    }

    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message, BindingResult bindingResult) {
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusTitle = status.name();
        this.statusText = status.getReasonPhrase();
        this.message = message;
        addErrors(bindingResult);
    }

    private void addErrors(BindingResult result) {
        this.errors = new HashMap<>();
        List<FieldError> fieldErrors = result.getFieldErrors();
        fieldErrors.forEach(error -> {
            this.errors.put(error.getField(), error.getDefaultMessage());
        });
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusTitle() {
        return statusTitle;
    }

    public void setStatusTitle(String statusTitle) {
        this.statusTitle = statusTitle;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
