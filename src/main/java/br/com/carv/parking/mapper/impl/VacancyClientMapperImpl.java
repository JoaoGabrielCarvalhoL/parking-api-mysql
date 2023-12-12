package br.com.carv.parking.mapper.impl;

import br.com.carv.parking.entity.VacancyClient;
import br.com.carv.parking.mapper.VacancyClientMapper;
import br.com.carv.parking.payload.request.VacancyClientPostRequest;
import br.com.carv.parking.payload.response.ParkingGetResponse;
import br.com.carv.parking.payload.response.VacancyClientGetResponse;
import org.springframework.stereotype.Service;

@Service
public class VacancyClientMapperImpl implements VacancyClientMapper {

    @Override
    public VacancyClient toVacancyClient(VacancyClientPostRequest vacancyClientPostRequest) {

        return new VacancyClient(vacancyClientPostRequest.license(), vacancyClientPostRequest.model(),
                vacancyClientPostRequest.brand(), vacancyClientPostRequest.color());

    }

    @Override
    public VacancyClientGetResponse toVacancyClientGetResponse (VacancyClient client) {
        return new VacancyClientGetResponse(client.getVacancy().getCode(), client.getLicensePlate(), client.getModelCar(),
                client.getBrandCar(), client.getCarColor(), client.getClient().getCertificatePerson(), client.getReceipt(),
                client.getEntryDate(), client.getDepartureDate(), client.getValue(), client.getDiscount());
    }
}
