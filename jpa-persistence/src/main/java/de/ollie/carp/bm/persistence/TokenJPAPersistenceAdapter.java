package de.ollie.carp.bm.persistence;

import static de.ollie.carp.bm.util.Check.ensure;

import de.ollie.carp.bm.core.exception.UniqueConstraintViolationException;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.service.factory.UUIDFactory;
import de.ollie.carp.bm.core.service.port.persistence.TokenPersistencePort;
import de.ollie.carp.bm.persistence.entity.TokenDBO;
import de.ollie.carp.bm.persistence.mapper.TokenDBOMapper;
import de.ollie.carp.bm.persistence.repository.TokenDBORepository;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenJPAPersistenceAdapter implements TokenPersistencePort {

	private final TokenDBOMapper mapper;
	private final TokenDBORepository repository;
	private final UUIDFactory uuidFactory;

	@Override
	public Token create(Token token) {
		token.setId(uuidFactory.create());
		return update(token);
	}

	@Override
	public void deleteById(UUID uuid) {
		repository.deleteById(uuid);
	}

	@Override
	public List<Token> findAll() {
		return mapper.toModels(repository.findAll());
	}

	@Override
	public Optional<Token> findById(UUID uuid) {
		return repository.findById(uuid).map(mapper::toModel);
	}

	@Override
	public Optional<Token> findByName(String name) {
		return repository.findByName(name).map(mapper::toModel);
	}

	@Override
	public Token update(Token token) {
		ensure(token.getId() != null, new IllegalStateException("Token has no id!"));
		if (repository.findByName(token.getName()).isPresent()) {
			throw new UniqueConstraintViolationException(token.getName(), TokenDBO.class.getSimpleName(), "name");
		}
		return mapper.toModel(repository.save(mapper.toDBO(token)));
	}
}
