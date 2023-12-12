package br.com.carv.parking.service;

import br.com.carv.parking.payload.request.VacancyClientPostRequest;
import br.com.carv.parking.payload.response.VacancyClientGetResponse;

public interface ParkingService {

    VacancyClientGetResponse checkIn(VacancyClientPostRequest vacancyClientPostRequest);

    VacancyClientGetResponse checkOut(String receipt);
}
