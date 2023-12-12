package br.com.carv.parking.controller.impl;

import br.com.carv.parking.controller.ParkingController;
import br.com.carv.parking.payload.request.VacancyClientPostRequest;
import br.com.carv.parking.payload.response.VacancyClientGetResponse;
import br.com.carv.parking.repository.projection.VacancyClientProjection;
import br.com.carv.parking.security.UserSecurity;
import br.com.carv.parking.service.ParkingService;
import br.com.carv.parking.service.VacancyClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/parking")
public class ParkingControllerImpl implements ParkingController {

    private final ParkingService parkingService;

    private final VacancyClientService vacancyClientService;

    public ParkingControllerImpl(ParkingService parkingService, VacancyClientService vacancyClientService) {
        this.parkingService = parkingService;
        this.vacancyClientService = vacancyClientService;
    }

    @Override
    public ResponseEntity<VacancyClientGetResponse> checkIn(VacancyClientPostRequest
                                                                        vacancyClientPostRequest) {
        VacancyClientGetResponse vacancyClientGetResponse =
                parkingService.checkIn(vacancyClientPostRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{receipt}")
                .buildAndExpand(vacancyClientGetResponse.receipt())
                .toUri();

        return ResponseEntity.created(location).body(vacancyClientGetResponse);
    }

    @Override
    public ResponseEntity<VacancyClientGetResponse> getByReceipt(String receipt) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(vacancyClientService.getByReceipt(receipt));
    }

    @Override
    public ResponseEntity<VacancyClientGetResponse> checkOut(String receipt) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(parkingService.checkOut(receipt));
    }

    @Override
    public ResponseEntity<Page<VacancyClientProjection>> getAllParking(Pageable pageable, String certificate) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(vacancyClientService.getAllParking(pageable, certificate));
    }

    @Override
    public ResponseEntity<Page<VacancyClientGetResponse>> getAllParkingPerClient(Pageable pageable, UserSecurity userSecurity) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(vacancyClientService.findAllPerClientId(userSecurity.getId(), pageable));
    }


}
