package br.com.carv.parking.mapper;

import br.com.carv.parking.entity.VacancyClient;
import br.com.carv.parking.payload.request.VacancyClientPostRequest;
import br.com.carv.parking.payload.response.ParkingGetResponse;
import br.com.carv.parking.payload.response.VacancyClientGetResponse;

public interface VacancyClientMapper {

    VacancyClient toVacancyClient(VacancyClientPostRequest vacancyClientPostRequest);

    VacancyClientGetResponse toVacancyClientGetResponse (VacancyClient client);
}
