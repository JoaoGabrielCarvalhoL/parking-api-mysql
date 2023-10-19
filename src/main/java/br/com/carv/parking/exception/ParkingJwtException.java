package br.com.carv.parking.exception;

public class ParkingJwtException extends RuntimeException {
    public ParkingJwtException(String message) {
        super(message);
    }
}
