package de.ollie.carp.bm.persistence.repository;

import de.ollie.carp.bm.persistence.entity.SitzungDBO;
import de.ollie.carp.bm.persistence.entity.UserDBO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SitzungDBORepository extends JpaRepository<SitzungDBO, Long> {
	List<SitzungDBO> findAllByUser(UserDBO user);

	Optional<SitzungDBO> findByGlobalId(String globalId);

	List<SitzungDBO> findAllByUrlSuffix(String urlSuffix);
}
