package br.com.carv.parking.controller.impl;

import br.com.carv.parking.controller.VacancyController;
import br.com.carv.parking.payload.request.VacancyPostRequest;
import br.com.carv.parking.payload.request.VacancyPutRequest;
import br.com.carv.parking.payload.response.VacancyGetResponse;
import br.com.carv.parking.payload.response.VacancyResponse;
import br.com.carv.parking.service.VacancyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/vacancies")
public class VacancyControllerImpl implements VacancyController {

    private final VacancyService vacancyService;

    public VacancyControllerImpl(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }
    @Override
    public ResponseEntity<EntityModel<VacancyResponse>> save(VacancyPostRequest vacancyPostRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.vacancyService.save(vacancyPostRequest));
    }

    @Override
    public ResponseEntity<VacancyGetResponse> findById(UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.vacancyService.findById(id));
    }

    @Override
    public ResponseEntity<Page<VacancyGetResponse>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.vacancyService.findAll(pageable));
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        this.vacancyService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Void> update(VacancyPutRequest vacancyPutRequest) {
        this.vacancyService.update(vacancyPutRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
