package de.ollie.carp.bm.lib.server.persistence;

import de.ollie.carp.bm.lib.server.core.model.Seite;
import de.ollie.carp.bm.lib.server.core.model.SeitenParameter;
import de.ollie.carp.bm.lib.server.core.model.Token;
import de.ollie.carp.bm.lib.server.core.service.port.TokenPersistencePort;
import de.ollie.carp.bm.lib.server.persistence.entity.PageDBOMapper;
import de.ollie.carp.bm.lib.server.persistence.entity.PageableMapper;
import de.ollie.carp.bm.lib.server.persistence.entity.TokenDBO;
import de.ollie.carp.bm.lib.server.persistence.mapper.TokenDBOMapper;
import de.ollie.carp.bm.lib.server.persistence.repository.TokenDBORepository;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class TokenJPAPersistenceAdapter implements TokenPersistencePort {

	private final PageDBOMapper<Token, TokenDBO> pageMapper;
	private final PageableMapper pageableMapper;
	private final TokenDBOMapper mapper;
	private final TokenDBORepository repository;

	@Override
	public Seite<Token> findAllTokens(SeitenParameter seitenParameter) {
		return pageMapper.toModel(repository.findAll(pageableMapper.toDbo(seitenParameter)), mapper);
	}
}
