package de.ollie.carp.bm.client;

import de.ollie.carp.bm.core.model.Token;
import java.util.List;

public interface TokenClient {
	Token createTokenWithName(String name);

	List<Token> findAllTokens();
}
