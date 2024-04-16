package edu.ib.technologiebyadamski.infrastructure.repository;

import edu.ib.technologiebyadamski.infrastructure.entity.RentalEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<RentalEntity, Long> {
}
