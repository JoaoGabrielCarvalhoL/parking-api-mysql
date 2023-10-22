package br.com.carv.parking.payload.response;

import br.com.carv.parking.enumerations.VacancyStatus;

import java.io.Serializable;
import java.util.UUID;

public class VacancyResponse implements Serializable {
    private UUID id;
    private String code;
    private VacancyStatus status;

    public VacancyResponse() {}

    public VacancyResponse(UUID id, String code, VacancyStatus status) {
        this.id = id;
        this.code = code;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public VacancyStatus getStatus() {
        return status;
    }

    public void setStatus(VacancyStatus status) {
        this.status = status;
    }
}
