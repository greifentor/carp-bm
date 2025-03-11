package de.ollie.carp.bm.core.service;

import de.ollie.carp.bm.core.model.BattleMapToken;

public interface SelectedTokenService {
	void selectToken(BattleMapToken battleMapToken);

	void unselectToken(BattleMapToken battleMapToken);
}
