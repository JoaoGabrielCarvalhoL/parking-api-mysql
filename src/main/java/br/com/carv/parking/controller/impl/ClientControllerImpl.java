package br.com.carv.parking.controller.impl;

import br.com.carv.parking.controller.ClientController;
import br.com.carv.parking.payload.request.ClientPostRequest;
import br.com.carv.parking.payload.request.ClientPutRequest;
import br.com.carv.parking.payload.response.ClientGetResponse;
import br.com.carv.parking.payload.response.ClientResponse;
import br.com.carv.parking.security.UserSecurity;
import br.com.carv.parking.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/clients")
public class ClientControllerImpl implements ClientController {

    private final ClientService clientService;

    public ClientControllerImpl(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ResponseEntity<EntityModel<ClientResponse>> save(UserSecurity userSecurity, ClientPostRequest clientPostRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clientService.save(userSecurity, clientPostRequest));
    }

    @Override
    public ResponseEntity<ClientGetResponse> findById(UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.clientService.findById(id));
    }

    @Override
    public ResponseEntity<Page<ClientGetResponse>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.clientService.findAll(pageable));
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        this.clientService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Void> update(UserSecurity userSecurity, ClientPutRequest clientPutRequest) {
        this.clientService.update(userSecurity, clientPutRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
