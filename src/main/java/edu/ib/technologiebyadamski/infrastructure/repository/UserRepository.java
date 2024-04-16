package edu.ib.technologiebyadamski.infrastructure.repository;


import edu.ib.technologiebyadamski.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
