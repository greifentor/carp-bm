package de.ollie.carp.maps.rest.api.core.service;

import de.ollie.carp.maps.rest.api.core.model.Seite;
import de.ollie.carp.maps.rest.api.core.model.SeitenParameter;
import de.ollie.carp.maps.rest.api.core.model.Token;

public interface MapsTokenImportService {
	Seite<Token> findAllTokens(SeitenParameter seitenParameter);
}
