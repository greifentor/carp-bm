package de.ollie.carp.bm.gui;

import de.ollie.carp.bm.core.model.BattleMapToken;
import java.awt.Graphics2D;

public interface TokenSetterService {
	void setTokenToBattleMap(BattleMapToken bmt, Graphics2D graphics);
}
