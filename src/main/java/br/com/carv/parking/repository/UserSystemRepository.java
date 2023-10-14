package br.com.carv.parking.repository;

import br.com.carv.parking.entity.UserSystem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.Optional;

public interface UserSystemRepository extends JpaRepository<UserSystem, UUID> {

    Optional<UserSystem> findByUsername(String username);

    Optional<UserSystem> findByEmail(String email);
}
