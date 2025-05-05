package de.ollie.carp.maps.rest.api.core.service.impl;

import static de.ollie.carp.bm.util.Check.ensure;

import de.ollie.carp.maps.rest.api.core.model.Seite;
import de.ollie.carp.maps.rest.api.core.model.SeitenParameter;
import de.ollie.carp.maps.rest.api.core.model.Token;
import de.ollie.carp.maps.rest.api.core.service.MapsTokenImportService;
import de.ollie.carp.maps.rest.api.core.service.port.MapsTokenImportPersistencePort;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class MapsTokenImportServiceImpl implements MapsTokenImportService {

	private final MapsTokenImportPersistencePort persistencePort;

	@Override
	public Seite<Token> findAllTokens(SeitenParameter seitenParameter) {
		ensure(seitenParameter != null, "seiten parameter cannot be null!");
		return persistencePort.findAllTokens(seitenParameter);
	}
}
