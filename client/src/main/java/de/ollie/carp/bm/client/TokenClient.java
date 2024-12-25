package de.ollie.carp.bm.client;

import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Token;
import java.util.List;
import java.util.UUID;

public interface TokenClient {
	Token createTokenWithName(String name);

	UUID delete(String uuidOrName);

	List<Token> findAllTokens();

	void setTokenToBattleMapOfSpielrunde(Token token, BattleMap battleMap, Coordinates coordinates);
}
