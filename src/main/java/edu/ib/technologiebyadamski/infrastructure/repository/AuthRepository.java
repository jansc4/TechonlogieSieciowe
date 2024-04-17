package edu.ib.technologiebyadamski.infrastructure.repository;

import edu.ib.technologiebyadamski.infrastructure.entity.AuthEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<AuthEntity, Long> {
    Optional<AuthEntity> findByUserName(String username);
}
