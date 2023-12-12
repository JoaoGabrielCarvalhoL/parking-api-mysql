package br.com.carv.parking.service.impl;

import br.com.carv.parking.entity.VacancyClient;
import br.com.carv.parking.exception.ResourceNotFoundException;
import br.com.carv.parking.mapper.VacancyClientMapper;
import br.com.carv.parking.payload.request.VacancyClientPostRequest;
import br.com.carv.parking.payload.response.VacancyClientGetResponse;
import br.com.carv.parking.repository.VacancyClientRepository;
import br.com.carv.parking.repository.projection.VacancyClientProjection;
import br.com.carv.parking.service.VacancyClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class VacancyClientServiceImpl implements VacancyClientService {

    private final VacancyClientRepository vacancyClientRepository;
    private final VacancyClientMapper vacancyClientMapper;
    private final Logger logger = Logger.getLogger(VacancyClientServiceImpl.class.getName());

    public VacancyClientServiceImpl(VacancyClientRepository vacancyClientRepository,
                                    VacancyClientMapper vacancyClientMapper) {
        this.vacancyClientRepository = vacancyClientRepository;
        this.vacancyClientMapper = vacancyClientMapper;
    }

    @Override
    public VacancyClientGetResponse save(VacancyClient vacancyClient) {
        logger.info("Saving into database.");
        VacancyClient saved = this.vacancyClientRepository
                .save(vacancyClient);
        return vacancyClientMapper.toVacancyClientGetResponse(saved);
    }

    @Override
    public VacancyClientGetResponse getByReceipt(String receipt) {
        return vacancyClientRepository.findByReceiptAndDepartureDateIsNull(receipt)
                .map(vacancyClientMapper::toVacancyClientGetResponse)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vacancy Client not found into database. Receipt: " + receipt));
    }

    @Override
    public VacancyClient getEntityByReceipt(String receipt) {
        return vacancyClientRepository.findByReceiptAndDepartureDateIsNull(receipt)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vacancy Client not found into database. Receipt: " + receipt));
    }

    @Override
    public long getTotalParkingTimes(String certificatePerson) {
        return vacancyClientRepository
                .countByClientCertificatePersonAndDepartureDateIsNotNull(certificatePerson);
    }

    @Override
    public PageImpl<VacancyClientProjection> getAllParking(Pageable pageable, String certificate) {
        return findAllByCertificate(certificate, pageable);

    }

    @Override
    public Page<VacancyClientGetResponse> findAllPerClientId(UUID id, Pageable pageable) {
        return vacancyClientRepository.findAllByClientUserSystemId(id, pageable);
    }

    private PageImpl<VacancyClientProjection> findAllByCertificate(String certificate, Pageable pageable) {
        return this.vacancyClientRepository.findAllByClientCertificatePerson(certificate, pageable);
    }
}
