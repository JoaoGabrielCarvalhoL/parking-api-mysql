package br.com.carv.parking.service;

import br.com.carv.parking.entity.VacancyClient;
import br.com.carv.parking.payload.request.VacancyClientPostRequest;
import br.com.carv.parking.payload.response.VacancyClientGetResponse;
import br.com.carv.parking.repository.projection.VacancyClientProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface VacancyClientService {

    VacancyClientGetResponse save(final VacancyClient vacancyClient);

    VacancyClientGetResponse getByReceipt(final String receipt);

    VacancyClient getEntityByReceipt(final String receipt);

    long getTotalParkingTimes(final String certificatePerson);

    PageImpl<VacancyClientProjection> getAllParking(final Pageable pageable, final String certificate);

    Page<VacancyClientGetResponse> findAllPerClientId(UUID id, Pageable pageable);
}
