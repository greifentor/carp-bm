package de.ollie.carp.bm.core.service.impl;

import static de.ollie.carp.bm.util.Check.ensure;

import de.ollie.carp.bm.core.exception.NoSuchRecordException;
import de.ollie.carp.bm.core.model.TokenData;
import de.ollie.carp.bm.core.service.TokenDataService;
import de.ollie.carp.bm.core.service.factory.UUIDFactory;
import de.ollie.carp.bm.core.service.port.persistence.TokenDataPersistencePort;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenDataServiceImpl implements TokenDataService {

	private final TokenDataPersistencePort persistencePort;
	private final UUIDFactory uuidFactory;

	@Override
	public TokenData create(TokenData tokenData) {
		ensure(tokenData != null, "token data cannot be null.");
		tokenData.setId(uuidFactory.create());
		return persistencePort.save(tokenData);
	}

	@Override
	public TokenData deleteById(UUID id) {
		ensure(id != null, "id cannot be null.");
		TokenData deletedTokenData = findById(id)
			.orElseThrow(() -> new NoSuchRecordException(id.toString(), "TokenData", "id"));
		persistencePort.deleteById(id);
		return deletedTokenData;
	}

	@Override
	public List<TokenData> findAll() {
		return persistencePort.findAll();
	}

	@Override
	public Optional<TokenData> findById(UUID id) {
		return persistencePort.findById(id);
	}
}
