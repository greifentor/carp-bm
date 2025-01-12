package de.ollie.carp.bm.persistence;

import static de.ollie.carp.bm.util.Check.ensure;

import de.ollie.carp.bm.core.model.TokenData;
import de.ollie.carp.bm.core.service.port.persistence.TokenDataPersistencePort;
import de.ollie.carp.bm.persistence.mapper.TokenDataDBOMapper;
import de.ollie.carp.bm.persistence.repository.TokenDataDBORepository;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenDataJPAPersistenceAdapter implements TokenDataPersistencePort {

	private final TokenDataDBOMapper mapper;
	private final TokenDataDBORepository repository;

	@Override
	public void deleteById(UUID uuid) {
		repository.deleteById(uuid);
	}

	@Override
	public List<TokenData> findAll() {
		return mapper.toModels(repository.findAll());
	}

	@Override
	public Optional<TokenData> findById(UUID uuid) {
		return repository.findById(uuid).map(mapper::toModel);
	}

	@Override
	public TokenData save(TokenData tokenData) {
		ensure(tokenData != null, "token data cannot be null!");
		return mapper.toModel(repository.save(mapper.toDBO(tokenData)));
	}
}
