package de.ollie.carp.bm.persistence;

import de.ollie.carp.bm.core.exception.UniqueConstraintViolationException;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.service.port.persistence.TokenPersistencePort;
import de.ollie.carp.bm.persistence.entity.TokenDBO;
import de.ollie.carp.bm.persistence.factory.TokenDBOFactory;
import de.ollie.carp.bm.persistence.mapper.TokenDBOMapper;
import de.ollie.carp.bm.persistence.repository.TokenDBORepository;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenJPAPersistenceAdapter implements TokenPersistencePort {

	private final TokenDBOFactory factory;
	private final TokenDBOMapper mapper;
	private final TokenDBORepository repository;

	@Override
	public Token createTokenWithName(String name) {
		if (repository.findByName(name).isPresent()) {
			throw new UniqueConstraintViolationException(name, TokenDBO.class.getSimpleName(), "name");
		}
		TokenDBO tokenDBO = factory.createWithName(name);
		return mapper.toModel(repository.save(tokenDBO));
	}
}