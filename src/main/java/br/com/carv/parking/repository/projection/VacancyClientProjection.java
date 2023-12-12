package br.com.carv.parking.repository.projection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface VacancyClientProjection {
    String getCode();
    String getLicense();
    String getModel();
    String getBrand();
    String getColor();
    @JsonProperty("CPF")
    String getCertificatePersonClient();
    String getReceipt();
    LocalDateTime getEntryDate();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    LocalDateTime departureDate();
    BigDecimal value();
    BigDecimal discount();
}
