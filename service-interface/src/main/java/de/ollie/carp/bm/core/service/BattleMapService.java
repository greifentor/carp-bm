package de.ollie.carp.bm.core.service;

import de.ollie.carp.bm.core.model.BattleMap;
import java.util.List;
import java.util.Optional;

public interface BattleMapService {
	BattleMap create(BattleMap battleMap);

	BattleMap delete(String uuidOrName);

	List<BattleMap> findAll();

	Optional<BattleMap> findByIdOrName(String battleMapIdOrName);
}
