package de.ollie.carp.bm.core.service.impl;

import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.service.BattleMapService;
import de.ollie.carp.bm.core.service.factory.UUIDFactory;
import de.ollie.carp.bm.core.service.port.persistence.BattleMapPersistencePort;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class BattleMapServiceImpl implements BattleMapService {

	private final BattleMapPersistencePort battleMapPersistencePort;

	private final UUIDFactory uuidFactory;

	@Override
	public BattleMap create(BattleMap battleMap) {
		battleMap.setId(uuidFactory.create());
		return battleMapPersistencePort.create(battleMap);
	}

	@Override
	public BattleMap delete(String uuidOrName) {
		UUID id = battleMapPersistencePort
			.findByName(uuidOrName)
			.map(BattleMap::getId)
			.orElseGet(() -> uuidFactory.createFromString(uuidOrName));
		BattleMap deletedBattleMap = battleMapPersistencePort.findById(id).orElse(null);
		battleMapPersistencePort.deleteById(id);
		return deletedBattleMap;
	}

	@Override
	public List<BattleMap> findAll() {
		return battleMapPersistencePort.findAll();
	}

	@Override
	public Optional<BattleMap> findById(UUID battleMapId) {
		return battleMapPersistencePort.findById(battleMapId);
	}

	@Override
	public Optional<BattleMap> findByName(String name) {
		return battleMapPersistencePort.findByName(name);
	}
}
