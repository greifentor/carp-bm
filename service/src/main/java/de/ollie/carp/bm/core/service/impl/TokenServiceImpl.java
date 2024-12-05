package de.ollie.carp.bm.core.service.impl;

import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Sitzung;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.service.TokenService;
import de.ollie.carp.bm.core.service.port.persistence.SitzungTokenPersistencePort;
import jakarta.inject.Named;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

	private final SitzungTokenPersistencePort sitzungTokenPersistencePort;

	@Override
	public void addTokenToMapOfSitzung(Sitzung sitzung, Token token, Coordinates coordinates) {
		sitzungTokenPersistencePort.addTokenToMapOfSitzung(sitzung, token, coordinates);
	}

	@Override
	public Optional<Token> findById(UUID tokenId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
}
