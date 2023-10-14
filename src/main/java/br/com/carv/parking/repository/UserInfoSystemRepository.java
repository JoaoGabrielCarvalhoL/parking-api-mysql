package br.com.carv.parking.repository;

import br.com.carv.parking.entity.UserInfoSystem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserInfoSystemRepository extends JpaRepository<UserInfoSystem, UUID> {
}
