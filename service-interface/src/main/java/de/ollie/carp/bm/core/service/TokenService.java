package de.ollie.carp.bm.core.service;

import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Token;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenService {
	void addTokenToBattleMap(Token token, BattleMap battleMap, Coordinates coordinates);

	Token create(Token token);

	Token delete(String uuidOrName);

	List<Token> findAll();

	Optional<Token> findById(UUID tokenId);

	Optional<Token> findByName(String name);
}
