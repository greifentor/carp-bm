package de.ollie.carp.maps.rest.api.persistence.entity;

import de.ollie.carp.maps.rest.api.persistence.mapper.PageDBOMapper;
import de.ollie.carp.maps.rest.api.persistence.mapper.PageableMapper;
import de.ollie.carp.maps.rest.api.persistence.mapper.TokenDBOMapper;
import de.ollie.carp.maps.rest.api.persistence.repository.TokenDBORepository;
import de.ollie.carp.maps.rest.api.service.model.Seite;
import de.ollie.carp.maps.rest.api.service.model.SeitenParameter;
import de.ollie.carp.maps.rest.api.service.model.Token;
import de.ollie.carp.maps.rest.api.service.port.TokenPersistencePort;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class TokenJPAPersistenceAdapter implements TokenPersistencePort {

	private final PageDBOMapper<Token, TokenDBO> pageMapper;
	private final PageableMapper pageableMapper;
	private final TokenDBORepository repository;
	private final TokenDBOMapper mapper;

	@Override
	public Seite<Token> findAll(SeitenParameter seitenParameter) {
		return pageMapper.toModel(repository.findAll(pageableMapper.toDbo(seitenParameter)), mapper);
	}
}
