package de.ollie.carp.bm.client;

import de.ollie.carp.bm.core.model.BattleMap;
import java.util.List;
import java.util.UUID;

public interface BattleMapClient {
	BattleMap createBattleMap(String name, byte[] imageContent, int fieldSizeInPixels, int offsetInPixels);

	UUID delete(String uuidOrName);

	List<BattleMap> findAllBattleMaps();
}
