package de.ollie.carp.bm.core.service.port.persistence;

import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Spielrunde;
import de.ollie.carp.bm.core.model.SpielrundeToken;
import de.ollie.carp.bm.core.model.Token;

public interface SpielrundeTokenPersistencePort {
	SpielrundeToken addTokenToMapOfSpielrunde(Spielrunde sitzung, Token token, Coordinates coordinates);
}
