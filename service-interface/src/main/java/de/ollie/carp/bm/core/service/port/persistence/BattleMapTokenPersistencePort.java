package de.ollie.carp.bm.core.service.port.persistence;

import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Token;
import java.util.List;
import java.util.UUID;

public interface BattleMapTokenPersistencePort {
	BattleMapToken addTokenToBattleMap(Token token, BattleMap battleMap, Coordinates coordinates);

	BattleMapToken deleteById(UUID id);

	List<BattleMapToken> findAll();

	BattleMapToken save(BattleMapToken battleMapToken);
}