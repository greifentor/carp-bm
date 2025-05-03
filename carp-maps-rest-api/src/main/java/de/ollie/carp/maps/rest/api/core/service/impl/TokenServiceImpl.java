package de.ollie.carp.maps.rest.api.core.service.impl;

import static de.ollie.carp.bm.util.Check.ensure;

import de.ollie.carp.maps.rest.api.core.model.Seite;
import de.ollie.carp.maps.rest.api.core.model.SeitenParameter;
import de.ollie.carp.maps.rest.api.core.model.Token;
import de.ollie.carp.maps.rest.api.core.service.TokenService;
import de.ollie.carp.maps.rest.api.core.service.port.TokenPersistencePort;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

	private final TokenPersistencePort persistencePort;

	@Override
	public Seite<Token> findBy(SeitenParameter seitenParameter) {
		ensure(seitenParameter != null, "seiten parameter cannot be null!");
		return persistencePort.findAll(seitenParameter);
	}
}
