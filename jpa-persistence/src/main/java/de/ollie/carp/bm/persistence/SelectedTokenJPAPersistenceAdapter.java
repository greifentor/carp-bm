package de.ollie.carp.bm.persistence;

import static de.ollie.carp.bm.util.Check.ensure;

import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.model.SelectedToken;
import de.ollie.carp.bm.core.service.port.persistence.SelectedTokenPersistencePort;
import de.ollie.carp.bm.persistence.mapper.BattleMapDBOMapper;
import de.ollie.carp.bm.persistence.mapper.SelectedTokenDBOMapper;
import de.ollie.carp.bm.persistence.repository.SelectedTokenDBORepository;
import jakarta.inject.Named;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class SelectedTokenJPAPersistenceAdapter implements SelectedTokenPersistencePort {

	private final BattleMapDBOMapper battleMapDBOMapper;
	private final SelectedTokenDBOMapper mapper;
	private final SelectedTokenDBORepository repository;

	@Override
	public void delete(SelectedToken selectedToken) {
		ensure(selectedToken != null, "selected token cannot be null!");
		repository.delete(mapper.toDBO(selectedToken));
	}

	@Override
	public Optional<SelectedToken> findSelectedTokenByBattleMap(BattleMap battleMap) {
		ensure(battleMap != null, "battle map cannot be null!");
		return repository.findByBattleMap(battleMapDBOMapper.toDBO(battleMap)).map(mapper::toModel);
	}

	@Override
	public SelectedToken save(SelectedToken selectedToken) {
		ensure(selectedToken != null, "selected token cannot be null!");
		return mapper.toModel(repository.save(mapper.toDBO(selectedToken)));
	}
}
