package de.ollie.carp.bm.core.service.impl;

import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Spielrunde;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.service.TokenService;
import de.ollie.carp.bm.core.service.port.persistence.SpielrundeTokenPersistencePort;
import de.ollie.carp.bm.core.service.port.persistence.TokenPersistencePort;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

	private final SpielrundeTokenPersistencePort sitzungTokenPersistencePort;
	private final TokenPersistencePort tokenPersistencePort;

	@Override
	public void addTokenToMapOfSitzung(Spielrunde sitzung, Token token, Coordinates coordinates) {
		sitzungTokenPersistencePort.addTokenToMapOfSpielrunde(sitzung, token, coordinates);
	}

	@Override
	public Token createTokenWithName(String name) {
		return tokenPersistencePort.createTokenWithName(name);
	}

	@Override
	public List<Token> findAll() {
		return tokenPersistencePort.findAll();
	}

	@Override
	public Optional<Token> findById(UUID tokenId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
}
