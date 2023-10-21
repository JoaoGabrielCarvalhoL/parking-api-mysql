package br.com.carv.parking.mapper;

import br.com.carv.parking.entity.Client;
import br.com.carv.parking.payload.request.ClientPostRequest;
import br.com.carv.parking.payload.request.ClientPutRequest;
import br.com.carv.parking.payload.response.ClientGetResponse;
import br.com.carv.parking.payload.response.ClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {

    Client toClient(ClientPostRequest clientPostRequest);

    Client toClient(ClientPutRequest clientPutRequest);

    ClientGetResponse toClientGetResponse(Client client);

    ClientResponse toClientResponse(Client client);
}
