package de.ollie.carp.maps.rest.api.persistence.entity;

import de.ollie.carp.maps.rest.api.core.model.Seite;
import de.ollie.carp.maps.rest.api.core.model.SeitenParameter;
import de.ollie.carp.maps.rest.api.core.model.Token;
import de.ollie.carp.maps.rest.api.core.service.port.MapsTokenImportPersistencePort;
import de.ollie.carp.maps.rest.api.persistence.mapper.PageDBOMapper;
import de.ollie.carp.maps.rest.api.persistence.mapper.PageableMapper;
import de.ollie.carp.maps.rest.api.persistence.mapper.TokenDBOMapper;
import de.ollie.carp.maps.rest.api.persistence.repository.TokenDBORepository;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class MapsTokenImportJPAPersistenceAdapter implements MapsTokenImportPersistencePort {

	private final PageDBOMapper<Token, TokenDBO> pageMapper;
	private final PageableMapper pageableMapper;
	private final TokenDBORepository repository;
	private final TokenDBOMapper mapper;

	@Override
	public Seite<Token> findAllTokens(SeitenParameter seitenParameter) {
		return pageMapper.toModel(repository.findAll(pageableMapper.toDbo(seitenParameter)), mapper);
	}
}
