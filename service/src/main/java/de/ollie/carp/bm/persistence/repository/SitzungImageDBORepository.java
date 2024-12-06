package de.ollie.carp.bm.persistence.repository;

import de.ollie.carp.bm.persistence.entity.SitzungDBO;
import de.ollie.carp.bm.persistence.entity.SitzungImageDBO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SitzungImageDBORepository extends JpaRepository<SitzungImageDBO, Long> {
	List<SitzungImageDBO> findAllBySitzung(SitzungDBO sitzung);

	Optional<SitzungImageDBO> findByGlobalId(String globalId);
}
