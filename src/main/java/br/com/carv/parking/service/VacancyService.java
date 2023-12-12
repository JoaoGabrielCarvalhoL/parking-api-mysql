package br.com.carv.parking.service;

import br.com.carv.parking.entity.Vacancy;
import br.com.carv.parking.payload.request.VacancyPostRequest;
import br.com.carv.parking.payload.request.VacancyPutRequest;
import br.com.carv.parking.payload.response.VacancyGetResponse;
import br.com.carv.parking.payload.response.VacancyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import java.util.UUID;

public interface VacancyService {

    void verifyCode(String code);

    EntityModel<VacancyResponse> save(final VacancyPostRequest vacancyPostRequest);

    VacancyGetResponse findById(final UUID id);

    Page<VacancyGetResponse> findAll(final Pageable pageable);

    void update(final VacancyPutRequest vacancyPutRequest);

    void delete(final UUID id);

    Vacancy findEntityById(final UUID id);

    Vacancy searchFreeVacancy();
}
