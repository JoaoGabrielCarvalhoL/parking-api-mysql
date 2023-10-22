package br.com.carv.parking.service.impl;

import br.com.carv.parking.controller.impl.VacancyControllerImpl;
import br.com.carv.parking.entity.Vacancy;
import br.com.carv.parking.exception.ResourceAlreadyUsedException;
import br.com.carv.parking.exception.ResourceNotFoundException;
import br.com.carv.parking.mapper.VacancyMapper;
import br.com.carv.parking.payload.request.VacancyPostRequest;
import br.com.carv.parking.payload.request.VacancyPutRequest;
import br.com.carv.parking.payload.response.VacancyGetResponse;
import br.com.carv.parking.payload.response.VacancyResponse;
import br.com.carv.parking.repository.VacancyRepository;
import br.com.carv.parking.service.VacancyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository vacancyRepository;
    private final VacancyMapper vacancyMapper;
    private final Logger logger = Logger.getLogger(VacancyServiceImpl.class.getName());

    public VacancyServiceImpl(VacancyRepository vacancyRepository, VacancyMapper vacancyMapper) {
        this.vacancyRepository = vacancyRepository;
        this.vacancyMapper = vacancyMapper;
    }

    @Override
    public void verifyCode(String code) {
        Optional<Vacancy> vacancy = this.vacancyRepository.findByCode(code);
        if (vacancy.isPresent()) {
            throw new ResourceAlreadyUsedException("Code Unavailable.");
        }
    }

    @Override
    public EntityModel<VacancyResponse> save(VacancyPostRequest vacancyPostRequest) {
        logger.info("Saving vacancy into database.");
        verifyCode(vacancyPostRequest.code());
        Vacancy saved = this.vacancyRepository.save(vacancyMapper.toVacancy(vacancyPostRequest));
        VacancyResponse vacancyResponse = vacancyMapper.toVacancyResponse(saved);
        EntityModel<VacancyResponse> entityModel = EntityModel.of(vacancyResponse);
        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VacancyControllerImpl.class)
                .findById(saved.getId())).withSelfRel();
        entityModel.add(link);
        return entityModel;
    }

    @Override
    public VacancyGetResponse findById(UUID id) {
        logger.info("Getting vacancy from database. Id: " + id);
        return this.vacancyRepository.findById(id).map(vacancyMapper::toVacancyGetResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Vacancy not found into database. Id: " + id));
    }

    @Override
    public Page<VacancyGetResponse> findAll(Pageable pageable) {
        logger.info("Getting all vacancies.");
        List<VacancyGetResponse> vacancies = this.vacancyRepository.findAll(pageable).map(vacancyMapper::toVacancyGetResponse)
                .toList();
        return new PageImpl<>(vacancies, pageable, vacancies.size());
    }

    @Override
    public void update(VacancyPutRequest vacancyPutRequest) {
        logger.info("Updating vacancy.");
        this.vacancyRepository.saveAndFlush(vacancyMapper.toVacancy(vacancyPutRequest));
    }

    @Override
    public void delete(UUID id) {
        logger.info("Deleting vacancy from database. Id: " + id);
        this.vacancyRepository.delete(findEntityById(id));
    }

    @Override
    public Vacancy findEntityById(UUID id) {
        logger.info("Getting entity from database. Id: " + id);
        return this.vacancyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vacancy not found into database. Id: " + id));
    }
}
