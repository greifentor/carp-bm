package de.ollie.carp.bm.core.service;

import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Spielrunde;
import de.ollie.carp.bm.core.model.Token;
import java.util.Optional;
import java.util.UUID;

public interface TokenService {
	Token createTokenWithName(String name);

	void addTokenToMapOfSitzung(Spielrunde sitzung, Token token, Coordinates coordinates);

	Optional<Token> findById(UUID tokenId);
}
