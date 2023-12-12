package br.com.carv.parking.service;

import br.com.carv.parking.entity.Client;
import br.com.carv.parking.payload.request.ClientPostRequest;
import br.com.carv.parking.payload.request.ClientPutRequest;
import br.com.carv.parking.payload.response.ClientGetResponse;
import br.com.carv.parking.payload.response.ClientResponse;
import br.com.carv.parking.security.UserSecurity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import java.util.UUID;

public interface ClientService {

    EntityModel<ClientResponse> save(final UserSecurity userSecurity, ClientPostRequest clientPostRequest);

    ClientGetResponse findById(final UUID id);

    Page<ClientGetResponse> findAll(final Pageable pageable);

    void update(final UserSecurity userSecurity, final ClientPutRequest clientPutRequest);

    void delete(final UUID id);

    Client findEntityById(final UUID id);

    void verifyCertificatePerson(final String certificatePerson);

    Client getClientByCertificatePerson(final String certificatePerson);
}
