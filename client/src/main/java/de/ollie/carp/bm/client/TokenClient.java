package de.ollie.carp.bm.client;

import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Token;
import java.util.List;
import java.util.UUID;

public interface TokenClient {
	Token createTokenWithName(String name);

	UUID delete(String tokenIdOrName);

	List<Token> findAllTokens();

	List<BattleMapToken> findAllByBattleMap(String battleMapIdOrName);

	String setTokenToBattleMapOfSpielrunde(String tokenIdOrName, String battleMapIdOrName, Coordinates coordinates);
}
