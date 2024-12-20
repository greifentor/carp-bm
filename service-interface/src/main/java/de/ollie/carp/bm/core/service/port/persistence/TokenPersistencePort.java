package de.ollie.carp.bm.core.service.port.persistence;

import de.ollie.carp.bm.core.model.Token;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenPersistencePort {
	Token create(Token token);

	void deleteById(UUID uuid);

	List<Token> findAll();

	Optional<Token> findById(UUID uuid);

	Optional<Token> findByName(String name);
}
