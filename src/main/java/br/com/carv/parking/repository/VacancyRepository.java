package br.com.carv.parking.repository;

import br.com.carv.parking.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VacancyRepository extends JpaRepository<Vacancy, UUID> {

    Optional<Vacancy> findByCode(String code);
}
