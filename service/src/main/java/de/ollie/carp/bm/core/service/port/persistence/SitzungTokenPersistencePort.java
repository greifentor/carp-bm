package de.ollie.carp.bm.core.service.port.persistence;

import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Sitzung;
import de.ollie.carp.bm.core.model.SitzungToken;
import de.ollie.carp.bm.core.model.Token;

public interface SitzungTokenPersistencePort {
	SitzungToken addTokenToMapOfSitzung(Sitzung sitzung, Token token, Coordinates coordinates);
}
