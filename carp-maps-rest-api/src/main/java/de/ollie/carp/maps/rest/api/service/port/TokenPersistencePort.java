package de.ollie.carp.maps.rest.api.service.port;

import de.ollie.carp.maps.rest.api.service.model.Seite;
import de.ollie.carp.maps.rest.api.service.model.SeitenParameter;
import de.ollie.carp.maps.rest.api.service.model.Token;

public interface TokenPersistencePort {
	Seite<Token> findAll(SeitenParameter seitenParameter);
}
