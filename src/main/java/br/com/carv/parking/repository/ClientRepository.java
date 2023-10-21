package br.com.carv.parking.repository;

import br.com.carv.parking.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    Optional<Client> findByCertificatePerson(String certificatePerson);
}
