package de.ollie.carp.bm.core.service.port.persistence;

import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.model.SelectedToken;
import java.util.List;
import java.util.Optional;

public interface SelectedTokenPersistencePort {
	void delete(SelectedToken tokenSelection);

	List<SelectedToken> findAll();

	Optional<SelectedToken> findSelectedTokenByBattleMap(BattleMap battleMap);

	SelectedToken save(SelectedToken tokenSelection);
}
