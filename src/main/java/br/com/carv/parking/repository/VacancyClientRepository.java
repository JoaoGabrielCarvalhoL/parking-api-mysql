package br.com.carv.parking.repository;

import br.com.carv.parking.entity.VacancyClient;
import br.com.carv.parking.payload.response.VacancyClientGetResponse;
import br.com.carv.parking.repository.projection.VacancyClientProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VacancyClientRepository extends JpaRepository<VacancyClient, UUID> {

   Optional<VacancyClient> findByReceiptAndDepartureDateIsNull(String receipt);

    long countByClientCertificatePersonAndDepartureDateIsNotNull(String certificatePerson);

    PageImpl<VacancyClientProjection> findAllByClientCertificatePerson(String certificate, Pageable pageable);

    Page<VacancyClientGetResponse> findAllByClientUserSystemId(UUID id, Pageable pageable);
}
