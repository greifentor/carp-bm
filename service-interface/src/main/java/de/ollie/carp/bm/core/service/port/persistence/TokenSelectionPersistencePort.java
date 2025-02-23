package de.ollie.carp.bm.core.service.port.persistence;

import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.model.TokenSelection;
import java.util.Optional;

public interface TokenSelectionPersistencePort {
	Optional<TokenSelection> findSelectedTokenByBattleMap(BattleMap battleMap);

	TokenSelection save(TokenSelection tokenSelection);
}
