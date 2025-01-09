package de.ollie.carp.bm.gui;

import de.ollie.carp.bm.gui.go.BattleMapTokenGO;
import java.awt.Graphics2D;
import java.util.List;

public interface TokenGUIService {
	boolean isHit(BattleMapTokenGO bmt, int mouseX, int mouseY);

	List<BattleMapTokenGO> reduceToHitTokens(List<BattleMapTokenGO> bmts, int mouseX, int mouseY);

	void setTokenToBattleMap(BattleMapTokenGO bmt, Graphics2D graphics);
}
