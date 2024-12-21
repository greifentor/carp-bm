package de.ollie.carp.bm.persistence;

import de.ollie.carp.bm.core.exception.UniqueConstraintViolationException;
import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.service.port.persistence.BattleMapPersistencePort;
import de.ollie.carp.bm.persistence.entity.BattleMapDBO;
import de.ollie.carp.bm.persistence.mapper.BattleMapDBOMapper;
import de.ollie.carp.bm.persistence.repository.BattleMapDBORepository;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class BattleMapJPAPersistenceAdapter implements BattleMapPersistencePort {

	private final BattleMapDBOMapper mapper;
	private final BattleMapDBORepository repository;

	@Override
	public BattleMap create(BattleMap battleMap) {
		if (repository.findByName(battleMap.getName()).isPresent()) {
			throw new UniqueConstraintViolationException(battleMap.getName(), BattleMapDBO.class.getSimpleName(), "name");
		}
		return mapper.toModel(repository.save(mapper.toDBO(battleMap)));
	}

	@Override
	public void deleteById(UUID uuid) {
		repository.deleteById(uuid);
	}

	@Override
	public List<BattleMap> findAll() {
		return mapper.toModels(repository.findAll());
	}

	@Override
	public Optional<BattleMap> findById(UUID uuid) {
		return repository.findById(uuid).map(mapper::toModel);
	}

	@Override
	public Optional<BattleMap> findByName(String name) {
		return repository.findByName(name).map(mapper::toModel);
	}
}
