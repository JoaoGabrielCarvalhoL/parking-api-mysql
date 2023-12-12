package br.com.carv.parking.service.impl;

import br.com.carv.parking.controller.impl.ClientControllerImpl;
import br.com.carv.parking.entity.Client;
import br.com.carv.parking.entity.UserSystem;
import br.com.carv.parking.exception.ResourceAlreadyUsedException;
import br.com.carv.parking.exception.ResourceNotFoundException;
import br.com.carv.parking.mapper.ClientMapper;
import br.com.carv.parking.payload.request.ClientPostRequest;
import br.com.carv.parking.payload.request.ClientPutRequest;
import br.com.carv.parking.payload.response.ClientGetResponse;
import br.com.carv.parking.payload.response.ClientResponse;
import br.com.carv.parking.repository.ClientRepository;
import br.com.carv.parking.security.UserSecurity;
import br.com.carv.parking.service.ClientService;
import br.com.carv.parking.service.UserSystemService;
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
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final UserSystemService userSystemService;
    private final ClientMapper clientMapper;
    private final Logger logger = Logger.getLogger(ClientServiceImpl.class.getName());

    public ClientServiceImpl(ClientRepository clientRepository, UserSystemService userSystemService,
                             ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.userSystemService = userSystemService;
        this.clientMapper = clientMapper;
    }

    @Override
    public EntityModel<ClientResponse> save(UserSecurity userSecurity, ClientPostRequest clientPostRequest) {
        logger.info("Saving client into database");
        verifyCertificatePerson(clientPostRequest.certificatePerson());
        UserSystem userSystem = userSystemService.findEntityById(userSecurity.getId());
        Client client = clientMapper.toClient(clientPostRequest);
        client.setUserSystem(userSystem);
        Client saved = this.clientRepository.save(client);
        ClientResponse clientResponse = clientMapper.toClientResponse(saved);
        EntityModel<ClientResponse> entityModel = EntityModel.of(clientResponse);
        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClientControllerImpl.class)
                .findById(saved.getId())).withSelfRel();
        entityModel.add(link);
        return entityModel;
    }

    @Override
    public ClientGetResponse findById(UUID id) {
        logger.info("Getting client by id: " + id);
        return this.clientRepository.findById(id)
                .filter(Client::getActive)
                .stream().findFirst()
                .map(clientMapper::toClientGetResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found into database. Id: " + id));
    }

    @Override
    public Page<ClientGetResponse> findAll(Pageable pageable) {
        logger.info("Getting all clients");
        List<ClientGetResponse> list = this.clientRepository.findAll(pageable).stream()
                .filter(Client::getActive)
                .map(clientMapper::toClientGetResponse)
                .toList();
        return new PageImpl<>(list, pageable, list.size());
    }

    @Override
    public void update(UserSecurity userSecurity, ClientPutRequest clientPutRequest) {
        logger.info("Updating client into database.");
        this.clientRepository.saveAndFlush(clientMapper.toClient(clientPutRequest));
    }

    @Override
    public void delete(UUID id) {
        logger.info("Deleting client by id: " + id);
        this.clientRepository.delete(findEntityById(id));
    }

    @Override
    public Client findEntityById(UUID id) {
        logger.info("Getting client entity by id: " + id);
        return this.clientRepository.findById(id)
                .filter(Client::getActive)
                .stream().findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Client not found into database. Id: " + id));
    }

    @Override
    public void verifyCertificatePerson(String certificatePerson) {
        Optional<Client> client = clientRepository.findByCertificatePerson(certificatePerson);
        if (client.isPresent()) {
            throw new ResourceAlreadyUsedException("Certificate Unavailable. Certificate Person must be unique.");
        }
    }

    @Override
    public Client getClientByCertificatePerson(String certificatePerson) {
        return clientRepository.findByCertificatePerson(certificatePerson)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found. CPF: " + certificatePerson));

    }
}
