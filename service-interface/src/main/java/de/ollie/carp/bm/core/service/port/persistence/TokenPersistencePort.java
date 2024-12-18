package de.ollie.carp.bm.core.service.port.persistence;

import de.ollie.carp.bm.core.model.Token;
import java.util.List;

public interface TokenPersistencePort {
	Token createTokenWithName(String name);

	List<Token> findAll();
}
