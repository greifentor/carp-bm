package de.ollie.carp.bm.persistence.repository;

import de.ollie.carp.bm.persistence.entity.SpielrundeDBO;
import de.ollie.carp.bm.persistence.entity.SpielrundeImageDBO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpielrundeImageDBORepository extends JpaRepository<SpielrundeImageDBO, Long> {
	List<SpielrundeImageDBO> findAllBySitzung(SpielrundeDBO sitzung);
}
