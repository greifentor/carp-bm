package de.ollie.carp.bm.lib.server.core.service.port;

import de.ollie.carp.bm.lib.server.core.model.Seite;
import de.ollie.carp.bm.lib.server.core.model.SeitenParameter;
import de.ollie.carp.bm.lib.server.core.model.Token;

public interface TokenPersistencePort {
	Seite<Token> findAllTokens(SeitenParameter seitenParameter);
}
