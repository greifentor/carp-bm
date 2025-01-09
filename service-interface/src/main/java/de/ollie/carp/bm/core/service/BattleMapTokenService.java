package de.ollie.carp.bm.core.service;

import de.ollie.carp.bm.core.model.BattleMapToken;
import java.util.Optional;
import java.util.UUID;

public interface BattleMapTokenService {
	Optional<BattleMapToken> findById(UUID id);

	BattleMapToken save(BattleMapToken battleMapToken);

	BattleMapToken remove(UUID id);
}
