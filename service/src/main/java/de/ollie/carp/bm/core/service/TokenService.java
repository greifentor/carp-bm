package de.ollie.carp.bm.core.service;

import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Sitzung;
import de.ollie.carp.bm.core.model.Token;

public interface TokenService {
	void addTokenToMapOfSitzung(Sitzung sitzung, Token token, Coordinates coordinates);
}
