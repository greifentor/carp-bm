package de.ollie.carp.bm.core.service.impl;

import static de.ollie.carp.bm.util.Check.ensure;

import de.ollie.carp.bm.core.exception.NoSuchRecordException;
import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.model.BattleMapTokenData;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.model.TokenSelection;
import de.ollie.carp.bm.core.service.TokenService;
import de.ollie.carp.bm.core.service.factory.UUIDFactory;
import de.ollie.carp.bm.core.service.port.persistence.BattleMapTokenPersistencePort;
import de.ollie.carp.bm.core.service.port.persistence.TokenPersistencePort;
import de.ollie.carp.bm.core.service.port.persistence.TokenSelectionPersistencePort;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

	private final BattleMapTokenPersistencePort battleMapTokenPersistencePort;
	private final TokenPersistencePort tokenPersistencePort;
	private final TokenSelectionPersistencePort tokenSelectionPersistencePort;
	private final UUIDFactory uuidFactory;

	@Override
	public void addTokenToBattleMap(Token token, BattleMap battleMap, BattleMapTokenData battleMapTokenData) {
		battleMapTokenPersistencePort.addTokenToBattleMap(token, battleMap, battleMapTokenData);
	}

	@Override
	public Token create(Token token) {
		token.setId(uuidFactory.create());
		return tokenPersistencePort.create(token);
	}

	@Override
	public Token delete(String uuidOrName) {
		Token deletedToken = findByIdOrName(uuidOrName)
			.orElseThrow(() -> new NoSuchRecordException(uuidOrName, "Token", "id"));
		tokenPersistencePort.deleteById(deletedToken.getId());
		return deletedToken;
	}

	@Override
	public List<Token> findAll() {
		return tokenPersistencePort.findAll();
	}

	@Override
	public List<BattleMapToken> findAllByBattleMap(BattleMap battleMap) {
		return battleMapTokenPersistencePort.findAllByBattleMap(battleMap);
	}

	@Override
	public Optional<Token> findByIdOrName(String tokenIdOrName) {
		return tokenPersistencePort
			.findByName(tokenIdOrName)
			.or(() -> tokenPersistencePort.findById(uuidFactory.createFromString(tokenIdOrName)));
	}

	@Override
	public Token selectTokenOnBattleMap(Token token, BattleMap battleMap, boolean selectState) {
		ensure(battleMap != null, "battle map cannot be null!");
		ensure(token != null, "token cannot be null!");
		Optional<TokenSelection> tokenSelectionBefore = tokenSelectionPersistencePort.findSelectedTokenByBattleMap(
			battleMap
		);
		return null;
	}
}
