package de.ollie.carp.bm.core.service;

import de.ollie.carp.bm.core.model.BattleMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BattleMapService {
	BattleMap create(BattleMap battleMap);

	BattleMap delete(String uuidOrName);

	List<BattleMap> findAll();

	Optional<BattleMap> findById(UUID battleMapId);

	Optional<BattleMap> findByName(String name);
}
