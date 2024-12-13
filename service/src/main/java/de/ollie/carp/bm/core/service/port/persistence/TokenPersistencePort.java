package de.ollie.carp.bm.core.service.port.persistence;

import de.ollie.carp.bm.core.model.Token;

public interface TokenPersistencePort {
	Token createTokenWithName(String name);
}
