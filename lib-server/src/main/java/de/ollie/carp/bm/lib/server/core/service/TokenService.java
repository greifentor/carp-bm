package de.ollie.carp.bm.lib.server.core.service;

import de.ollie.carp.bm.lib.server.core.model.Seite;
import de.ollie.carp.bm.lib.server.core.model.SeitenParameter;
import de.ollie.carp.bm.lib.server.core.model.Token;

public interface TokenService {
	Seite<Token> findAllTokens(SeitenParameter seitenParameter);

	void updateFromCarpMaps();
}
