package de.ollie.carp.bm.client.v1;

import de.ollie.carp.bm.client.v1.dto.BattleMapDTO;
import java.util.List;
import java.util.UUID;

public interface BattleMapClient {
	BattleMapDTO createBattleMap(String name, byte[] imageContent, int fieldSizeInPixels, int offsetInPixels);

	UUID delete(String uuidOrName);

	List<BattleMapDTO> findAllBattleMaps();
}
