package de.ollie.carp.maps.rest.api.persistence;

import de.ollie.carp.maps.rest.api.core.model.Seite;
import de.ollie.carp.maps.rest.api.core.model.SeitenParameter;
import de.ollie.carp.maps.rest.api.core.model.Token;
import de.ollie.carp.maps.rest.api.core.service.port.MapsTokenImportPersistencePort;
import de.ollie.carp.maps.rest.api.persistence.entity.ImageDBO;
import de.ollie.carp.maps.rest.api.persistence.mapper.ImageDBO2TokenMapper;
import de.ollie.carp.maps.rest.api.persistence.mapper.PageDBOMapper;
import de.ollie.carp.maps.rest.api.persistence.mapper.PageableMapper;
import de.ollie.carp.maps.rest.api.persistence.repository.ImageDBORepository;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class MapsTokenImportJPAPersistenceAdapter implements MapsTokenImportPersistencePort {

	private final PageDBOMapper<Token, ImageDBO> pageMapper;
	private final PageableMapper pageableMapper;
	private final ImageDBORepository repository;
	private final ImageDBO2TokenMapper mapper;

	@Override
	public Seite<Token> findAllTokens(SeitenParameter seitenParameter) {
		return pageMapper.toModel(repository.findAll(pageableMapper.toDbo(seitenParameter)), mapper);
	}
}
