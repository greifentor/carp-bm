package de.ollie.carp.bm.core.service.port.persistence;

import de.ollie.carp.bm.core.model.BattleMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BattleMapPersistencePort {
	BattleMap create(BattleMap battleMap);

	void deleteById(UUID uuid);

	List<BattleMap> findAll();

	Optional<BattleMap> findById(UUID uuid);

	Optional<BattleMap> findByName(String name);
}
