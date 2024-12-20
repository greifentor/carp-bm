package de.ollie.carp.bm.core.service.impl;

import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Spielrunde;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.service.TokenService;
import de.ollie.carp.bm.core.service.factory.UUIDFactory;
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

	private final UUIDFactory uuidFactory;

	@Override
	public void addTokenToMapOfSitzung(Spielrunde sitzung, Token token, Coordinates coordinates) {
		sitzungTokenPersistencePort.addTokenToMapOfSpielrunde(sitzung, token, coordinates);
	}

	@Override
	public Token create(Token token) {
		token.setId(uuidFactory.create());
		return tokenPersistencePort.create(token);
	}

	@Override
	public Token delete(String uuidOrName) {
		UUID id = tokenPersistencePort
			.findByName(uuidOrName)
			.map(Token::getId)
			.orElseGet(() -> uuidFactory.createFromString(uuidOrName));
		Token deletedToken = tokenPersistencePort.findById(id).orElse(null);
		tokenPersistencePort.deleteById(id);
		return deletedToken;
	}

	@Override
	public List<Token> findAll() {
		return tokenPersistencePort.findAll();
	}

	@Override
	public Optional<Token> findById(UUID tokenId) {
		return tokenPersistencePort.findById(tokenId);
	}

	@Override
	public Optional<Token> findByName(String name) {
		return tokenPersistencePort.findByName(name);
	}
}
