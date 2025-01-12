package de.ollie.carp.bm.client;

import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Token;
import java.util.List;
import java.util.UUID;

public interface TokenClient {
	Token createToken(String name, byte[] image);

	UUID delete(String tokenIdOrName);

	List<Token> findAllTokens();

	Token getByIdOrName(String idOrName);

	List<BattleMapToken> findAllByBattleMap(String battleMapIdOrName);

	String moveBattleMapToken(String battleMapTokenId, Coordinates coordinates);

	String setTokenToBattleMapOfSpielrunde(String tokenIdOrName, String battleMapIdOrName, Coordinates coordinates);
}
