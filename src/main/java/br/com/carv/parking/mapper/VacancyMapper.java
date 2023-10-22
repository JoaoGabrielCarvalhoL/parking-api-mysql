package br.com.carv.parking.mapper;

import br.com.carv.parking.entity.Vacancy;
import br.com.carv.parking.payload.request.VacancyPostRequest;
import br.com.carv.parking.payload.request.VacancyPutRequest;
import br.com.carv.parking.payload.response.VacancyGetResponse;
import br.com.carv.parking.payload.response.VacancyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VacancyMapper {

    Vacancy toVacancy(VacancyPostRequest vacancyPostRequest);

    Vacancy toVacancy(VacancyPutRequest vacancyPutRequest);

    VacancyGetResponse toVacancyGetResponse(Vacancy vacancy);

    VacancyResponse toVacancyResponse(Vacancy vacancy);
}
