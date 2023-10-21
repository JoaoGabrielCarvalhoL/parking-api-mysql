package br.com.carv.parking.payload.response;

import java.io.Serializable;
import java.util.UUID;

public class ClientResponse implements Serializable {

    private UUID id;
    private String name;
    private String certificatePerson;

    public ClientResponse() {}

    public ClientResponse(UUID id, String name, String certificatePerson) {
        this.id = id;
        this.name = name;
        this.certificatePerson = certificatePerson;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertificatePerson() {
        return certificatePerson;
    }

    public void setCertificatePerson(String certificatePerson) {
        this.certificatePerson = certificatePerson;
    }
}
