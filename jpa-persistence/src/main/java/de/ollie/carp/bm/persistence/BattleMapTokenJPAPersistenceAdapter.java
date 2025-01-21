package de.ollie.carp.bm.persistence;

import static de.ollie.carp.bm.util.Check.ensure;

import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.model.BattleMapTokenData;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.service.port.persistence.BattleMapTokenPersistencePort;
import de.ollie.carp.bm.persistence.entity.BattleMapTokenDBO;
import de.ollie.carp.bm.persistence.factory.BattleMapTokenDBOFactory;
import de.ollie.carp.bm.persistence.mapper.BattleMapDBOMapper;
import de.ollie.carp.bm.persistence.mapper.BattleMapTokenDBOMapper;
import de.ollie.carp.bm.persistence.mapper.TokenDBOMapper;
import de.ollie.carp.bm.persistence.repository.BattleMapTokenDBORepository;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class BattleMapTokenJPAPersistenceAdapter implements BattleMapTokenPersistencePort {

	private final BattleMapDBOMapper battleMapMapper;
	private final BattleMapTokenDBOFactory factory;
	private final BattleMapTokenDBOMapper mapper;
	private final BattleMapTokenDBORepository repository;
	private final TokenDBOMapper tokenMapper;

	@Override
	public BattleMapToken addTokenToBattleMap(Token token, BattleMap battleMap, BattleMapTokenData battleMapTokenData) {
		ensure(battleMap != null, "battle map cannot be null!");
		ensure(battleMapTokenData != null, "battle map token data cannot be null!");
		ensure(token != null, "token cannot be null!");
		BattleMapTokenDBO dbo = factory.create(battleMapMapper.toDBO(battleMap), tokenMapper.toDBO(token));
		dbo.setFieldX(battleMapTokenData.getCoordinates().getFieldX());
		dbo.setFieldY(battleMapTokenData.getCoordinates().getFieldY());
		BattleMapToken bmt = mapper.toModel(repository.save(dbo));
		repository.findAll().forEach(System.out::println);
		return bmt;
	}

	@Override
	public BattleMapToken deleteById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BattleMapToken> findAllByBattleMap(BattleMap battleMap) {
		return mapper.toModels(repository.findAllByBattleMap(battleMapMapper.toDBO(battleMap)));
	}

	@Override
	public Optional<BattleMapToken> findById(UUID id) {
		ensure(id != null, "id cannot be null.");
		return repository.findById(id).map(mapper::toModel);
	}

	@Override
	public BattleMapToken save(BattleMapToken battleMapToken) {
		ensure(battleMapToken != null, "battle map token cannot be null!");
		return mapper.toModel(repository.save(mapper.toDBO(battleMapToken)));
	}
}
