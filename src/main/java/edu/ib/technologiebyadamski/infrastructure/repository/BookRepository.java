package edu.ib.technologiebyadamski.infrastructure.repository;

import edu.ib.technologiebyadamski.infrastructure.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
