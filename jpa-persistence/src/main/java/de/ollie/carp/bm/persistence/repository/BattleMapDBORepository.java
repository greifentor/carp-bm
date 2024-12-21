package de.ollie.carp.bm.persistence.repository;

import de.ollie.carp.bm.persistence.entity.BattleMapDBO;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattleMapDBORepository extends JpaRepository<BattleMapDBO, UUID> {
	Optional<BattleMapDBO> findByName(String name);
}
