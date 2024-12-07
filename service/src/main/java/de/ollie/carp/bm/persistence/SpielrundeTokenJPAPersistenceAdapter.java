package de.ollie.carp.bm.persistence;

import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Spielrunde;
import de.ollie.carp.bm.core.model.SpielrundeToken;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.service.port.persistence.SpielrundeTokenPersistencePort;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class SpielrundeTokenJPAPersistenceAdapter implements SpielrundeTokenPersistencePort {

	@Override
	public SpielrundeToken addTokenToMapOfSpielrunde(Spielrunde spielrunde, Token token, Coordinates coordinates) {
		return null;
	}
}
