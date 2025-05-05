package de.ollie.carp.maps.rest.api.core.service.port;

import de.ollie.carp.maps.rest.api.core.model.Seite;
import de.ollie.carp.maps.rest.api.core.model.SeitenParameter;
import de.ollie.carp.maps.rest.api.core.model.Token;

public interface MapsTokenImportPersistencePort {
	Seite<Token> findAllTokens(SeitenParameter seitenParameter);
}
