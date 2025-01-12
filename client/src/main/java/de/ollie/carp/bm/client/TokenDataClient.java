package de.ollie.carp.bm.client;

import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.model.TokenData;
import java.util.List;
import java.util.UUID;

public interface TokenDataClient {
	TokenData createDnDTokenData(Token token, int maximumTp, int rk);

	UUID delete(UUID id);

	List<TokenData> findAllTokenData();

	TokenData save(TokenData tokenData);
}
