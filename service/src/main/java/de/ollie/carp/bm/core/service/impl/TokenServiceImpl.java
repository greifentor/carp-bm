package de.ollie.carp.bm.core.service.impl;

import static de.ollie.carp.bm.util.Check.ensure;

import de.ollie.carp.bm.core.exception.NoSuchRecordException;
import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.model.BattleMapTokenData;
import de.ollie.carp.bm.core.model.SelectedToken;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.service.TokenService;
import de.ollie.carp.bm.core.service.factory.UUIDFactory;
import de.ollie.carp.bm.core.service.port.persistence.BattleMapTokenPersistencePort;
import de.ollie.carp.bm.core.service.port.persistence.SelectedTokenPersistencePort;
import de.ollie.carp.bm.core.service.port.persistence.TokenPersistencePort;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

	private final BattleMapTokenPersistencePort battleMapTokenPersistencePort;
	private final TokenPersistencePort tokenPersistencePort;
	private final SelectedTokenPersistencePort selectionTokenPersistencePort;
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
		List<BattleMapToken> bmts = battleMapTokenPersistencePort.findAllByBattleMap(battleMap);
		selectionTokenPersistencePort
			.findSelectedTokenByBattleMap(battleMap)
			.ifPresent(selected ->
				bmts
					.stream()
					.filter(bmt -> bmt.getId().equals(selected.getId()))
					.findFirst()
					.ifPresent(bmt -> bmt.setSelected(true))
			);
		return bmts;
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
		SelectedToken selectedToken = selectionTokenPersistencePort.findSelectedTokenByBattleMap(battleMap).orElse(null);
		if (selectedToken != null) {
			Token selectedBefore = selectedToken.getBattleMapToken().getToken();
			selectedToken.getBattleMapToken().setToken(token);
			selectionTokenPersistencePort.save(selectedToken);
			return selectedBefore;
		}
		selectionTokenPersistencePort.save(
			new SelectedToken()
				.setBattleMap(battleMap)
				.setBattleMapToken(new BattleMapToken().setBattleMap(battleMap).setToken(token))
		);
		return null;
	}

	@Override
	public Optional<BattleMapToken> findSelectedTokenByBattleMap(BattleMap battleMap) {
		return selectionTokenPersistencePort.findSelectedTokenByBattleMap(battleMap).map(st -> st.getBattleMapToken());
	}
}
