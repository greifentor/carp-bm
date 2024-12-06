package de.ollie.carp.bm.persistence;

import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Sitzung;
import de.ollie.carp.bm.core.model.SitzungToken;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.service.port.persistence.SitzungTokenPersistencePort;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class SitzungTokenJPAPersistenceAdapter implements SitzungTokenPersistencePort {

	@Override
	public SitzungToken addTokenToMapOfSitzung(Sitzung sitzung, Token token, Coordinates coordinates) {
		return null;
	}
}
