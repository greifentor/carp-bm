package de.ollie.carp.bm.core.service;

import de.ollie.carp.bm.core.model.TokenData;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenDataService {
	TokenData create(TokenData tokenData);

	TokenData deleteById(UUID id);

	List<TokenData> findAll();

	Optional<TokenData> findById(UUID id);
}
