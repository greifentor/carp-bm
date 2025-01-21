package de.ollie.carp.bm.core.service;

import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.model.BattleMapTokenData;
import de.ollie.carp.bm.core.model.Token;
import java.util.List;
import java.util.Optional;

public interface TokenService {
	void addTokenToBattleMap(Token token, BattleMap battleMap, BattleMapTokenData battleMapTokenData);

	Token create(Token token);

	Token delete(String uuidOrName);

	List<Token> findAll();

	List<BattleMapToken> findAllByBattleMap(BattleMap battleMap);

	Optional<Token> findByIdOrName(String tokenIdOrName);
}
