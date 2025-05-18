package de.ollie.carp.bm.lib.server.core.service.impl;

import static de.ollie.carp.bm.util.Check.ensure;

import de.ollie.carp.bm.lib.server.core.model.Seite;
import de.ollie.carp.bm.lib.server.core.model.SeitenParameter;
import de.ollie.carp.bm.lib.server.core.model.Token;
import de.ollie.carp.bm.lib.server.core.service.TokenService;
import de.ollie.carp.bm.lib.server.core.service.port.TokenPersistencePort;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

	private final TokenPersistencePort persistencePort;

	@Override
	public Seite<Token> findAllTokens(SeitenParameter seitenParameter) {
		ensure(seitenParameter != null, "seiten parameter cannot be null!");
		return persistencePort.findAllTokens(seitenParameter);
	}

	@Override
	public void updateFromCarpMaps() {}
}
