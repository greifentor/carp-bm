package de.ollie.carp.bm.core.service;

import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Spielrunde;
import de.ollie.carp.bm.core.model.Token;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenService {
	void addTokenToMapOfSitzung(Spielrunde sitzung, Token token, Coordinates coordinates);

	Token createTokenWithName(String name);

	List<Token> findAll();

	Optional<Token> findById(UUID tokenId);
}
