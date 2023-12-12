package br.com.carv.parking.service.impl;

import br.com.carv.parking.entity.Client;
import br.com.carv.parking.entity.Vacancy;
import br.com.carv.parking.entity.VacancyClient;
import br.com.carv.parking.enumerations.VacancyStatus;
import br.com.carv.parking.mapper.VacancyClientMapper;
import br.com.carv.parking.payload.request.VacancyClientPostRequest;
import br.com.carv.parking.payload.response.VacancyClientGetResponse;
import br.com.carv.parking.service.ClientService;
import br.com.carv.parking.service.ParkingService;
import br.com.carv.parking.service.VacancyClientService;
import br.com.carv.parking.service.VacancyService;
import br.com.carv.parking.utils.ParkingUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class ParkingServiceImpl implements ParkingService {

    private final VacancyClientService vacancyClientService;
    private final ClientService clientService;
    private final VacancyService vacancyService;

    private final VacancyClientMapper vacancyClientMapper;

    private final Logger logger = Logger.getLogger(ParkingServiceImpl.class.getName());

    public ParkingServiceImpl(VacancyClientService vacancyClientService, ClientService clientService,
                              VacancyService vacancyService, VacancyClientMapper vacancyClientMapper) {
        this.vacancyClientService = vacancyClientService;
        this.clientService = clientService;
        this.vacancyService = vacancyService;
        this.vacancyClientMapper = vacancyClientMapper;
    }

    @Override
    public VacancyClientGetResponse checkIn(VacancyClientPostRequest vacancyClientPostRequest) {
        Client client = this.clientService
                .getClientByCertificatePerson(vacancyClientPostRequest.certificatePersonClient());
        VacancyClient vacancyClient = vacancyClientMapper.toVacancyClient(vacancyClientPostRequest);
        vacancyClient.setClient(client);

        Vacancy vacancy = vacancyService.searchFreeVacancy();
        vacancy.setStatus(VacancyStatus.OCCUPIED_VACANCY);
        vacancyClient.setVacancy(vacancy);
        vacancyClient.setEntryDate(LocalDateTime.now());
        vacancyClient.setReceipt(ParkingUtils.generateReceipt());
        return vacancyClientService.save(vacancyClient);
    }

    @Override
    public VacancyClientGetResponse checkOut(String receipt) {
        VacancyClient entity = vacancyClientService.getEntityByReceipt(receipt);
        BigDecimal totalValueToPaid = ParkingUtils.calculateCost(entity.getEntryDate(), LocalDateTime.now());
        long times = vacancyClientService.getTotalParkingTimes(entity.getClient().getCertificatePerson());
        BigDecimal discount = ParkingUtils.calculateDiscount(totalValueToPaid, times);
        entity.setValue(totalValueToPaid);
        entity.setDiscount(discount);
        entity.setDepartureDate(LocalDateTime.now());
        entity.getVacancy().setStatus(VacancyStatus.FREE_VACANCY);
        return vacancyClientService.save(entity);
    }
}
