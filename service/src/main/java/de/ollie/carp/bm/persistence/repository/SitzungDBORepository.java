package de.ollie.carp.bm.persistence.repository;

import de.ollie.carp.bm.persistence.entity.SitzungDBO;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SitzungDBORepository extends JpaRepository<SitzungDBO, Long> {
	Optional<SitzungDBO> findByGlobalId(String globalId);
}
