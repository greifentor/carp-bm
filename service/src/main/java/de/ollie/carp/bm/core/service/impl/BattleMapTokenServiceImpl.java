package de.ollie.carp.bm.core.service.impl;

import static de.ollie.carp.bm.util.Check.ensure;

import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.service.BattleMapTokenService;
import de.ollie.carp.bm.core.service.port.persistence.BattleMapTokenPersistencePort;
import jakarta.inject.Named;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class BattleMapTokenServiceImpl implements BattleMapTokenService {

	private final BattleMapTokenPersistencePort battleMapTokenPersistencePort;

	@Override
	public Optional<BattleMapToken> findById(UUID id) {
		ensure(id != null, "id cannot be null!");
		return battleMapTokenPersistencePort.findById(id);
	}

	@Override
	public BattleMapToken save(BattleMapToken battleMapToken) {
		ensure(battleMapToken != null, "battle map token cannot be null!");
		return battleMapTokenPersistencePort.save(battleMapToken);
	}

	@Override
	public BattleMapToken remove(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}
}
