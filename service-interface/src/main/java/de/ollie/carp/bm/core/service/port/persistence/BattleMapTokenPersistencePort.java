package de.ollie.carp.bm.core.service.port.persistence;

import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.model.BattleMapTokenData;
import de.ollie.carp.bm.core.model.Token;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BattleMapTokenPersistencePort {
	BattleMapToken addTokenToBattleMap(Token token, BattleMap battleMap, BattleMapTokenData battleMapTokenData);

	BattleMapToken deleteById(UUID id);

	Optional<BattleMapToken> findById(UUID id);

	List<BattleMapToken> findAllByBattleMap(BattleMap battleMap);

	BattleMapToken save(BattleMapToken battleMapToken);
}
