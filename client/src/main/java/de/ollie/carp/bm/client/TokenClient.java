package de.ollie.carp.bm.client;

import de.ollie.carp.bm.core.model.Token;

public interface TokenClient {
	Token createTokenWithName(String name);
}
