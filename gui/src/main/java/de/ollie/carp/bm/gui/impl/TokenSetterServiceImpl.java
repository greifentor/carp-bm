package de.ollie.carp.bm.gui.impl;

import static de.ollie.carp.bm.util.Check.ensure;

import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.gui.TokenSetterService;
import de.ollie.carp.bm.gui.factory.ImageIconFactory;
import jakarta.inject.Named;
import java.awt.Graphics2D;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenSetterServiceImpl implements TokenSetterService {

	private final ImageIconFactory imageIconFactory;

	@Override
	public void setTokenToBattleMap(BattleMapToken battleMapToken, Graphics2D graphics) {
		ensure(battleMapToken != null, "battle map token cannot be null!");
		ensure(graphics != null, "graphics cannot be null!");
		int fs = battleMapToken.getBattleMap().getFieldSizeInPixels();
		int offs = battleMapToken.getBattleMap().getOffsetInPixels();
		int x = (int) (fs * battleMapToken.getFieldX().doubleValue()) + offs;
		int y = (int) (fs * battleMapToken.getFieldY().doubleValue()) + offs;
		graphics.drawImage(imageIconFactory.create(battleMapToken.getToken().getImage()), x, y, null);
	}
}
