package de.ollie.carp.bm.core.service.impl;

import static de.ollie.carp.bm.util.Check.ensure;

import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.model.SelectedToken;
import de.ollie.carp.bm.core.service.SelectedTokenService;
import de.ollie.carp.bm.core.service.port.persistence.SelectedTokenPersistencePort;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named
@RequiredArgsConstructor
class SelectedTokenServiceImpl implements SelectedTokenService {

	private static final Logger LOG = LogManager.getLogger(SelectedTokenServiceImpl.class);

	private final SelectedTokenPersistencePort selectedTokenPersistencePort;

	@Override
	public void selectToken(BattleMapToken battleMapToken) {
		ensure(battleMapToken != null, "battle map token cannot be null!");
		LOG.info("select token ({})", battleMapToken.getId());
		SelectedToken selectedToken = selectedTokenPersistencePort
			.findSelectedTokenByBattleMap(battleMapToken.getBattleMap())
			.orElseGet(() -> new SelectedToken().setBattleMap(battleMapToken.getBattleMap()).setBattleMapToken(battleMapToken)
			);
		selectedTokenPersistencePort.save(selectedToken);
	}

	@Override
	public void unselectToken(BattleMapToken battleMapToken) {
		ensure(battleMapToken != null, "battle map token cannot be null!");
		LOG.info("unselect token ({})", battleMapToken.getId());
		SelectedToken selectedToken = selectedTokenPersistencePort
			.findSelectedTokenByBattleMap(battleMapToken.getBattleMap())
			.orElseGet(() -> new SelectedToken().setBattleMap(battleMapToken.getBattleMap()).setBattleMapToken(battleMapToken)
			);
		selectedTokenPersistencePort.delete(selectedToken);
	}
}
