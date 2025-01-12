package de.ollie.carp.bm.core.service.port.persistence;

import de.ollie.carp.bm.core.model.TokenData;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenDataPersistencePort {
	void deleteById(UUID uuid);

	List<TokenData> findAll();

	Optional<TokenData> findById(UUID uuid);

	TokenData save(TokenData tokenData);
}
