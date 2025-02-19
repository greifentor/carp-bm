package de.ollie.carp.bm.gui.impl;

import static de.ollie.carp.bm.util.Check.ensure;

import de.ollie.carp.bm.gui.TokenGUIService;
import de.ollie.carp.bm.gui.factory.ShapeFactory;
import de.ollie.carp.bm.gui.go.BattleMapTokenGO;
import de.ollie.carp.bm.gui.go.CoordinatesXYGO;
import de.ollie.carp.bm.gui.go.DnDBattleMapTokenGO;
import de.ollie.carp.bm.gui.go.DnDTokenGO;
import jakarta.inject.Named;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenGUIServiceImpl implements TokenGUIService {

	static final int TRANSPARENT = 0;

	private final ShapeFactory shapeFactory;

	@Override
	public boolean isHit(BattleMapTokenGO battleMapToken, int mouseX, int mouseY) {
		ensure(battleMapToken != null, "battle map token cannot be null!");
		CoordinatesXYGO leftUpperCorner = battleMapToken.getTokenLeftUpperCorner();
		Shape shape = shapeFactory.create(leftUpperCorner, battleMapToken.getToken().getBufferedImage());
		return shape.contains(mouseX, mouseY) && !isTransparent(battleMapToken, leftUpperCorner, mouseX, mouseY);
	}

	private boolean isTransparent(
		BattleMapTokenGO battleMapToken,
		CoordinatesXYGO leftUpperCorner,
		int mouseX,
		int mouseY
	) {
		return (
			battleMapToken
				.getToken()
				.getBufferedImage()
				.getRGB(mouseX - leftUpperCorner.getX(), mouseY - leftUpperCorner.getY()) ==
			TRANSPARENT
		);
	}

	@Override
	public List<BattleMapTokenGO> reduceToHitTokens(List<BattleMapTokenGO> battleMapTokens, int mouseX, int mouseY) {
		return battleMapTokens.stream().filter(bmt -> isHit(bmt, mouseX, mouseY)).toList();
	}

	@Override
	public void setTokenToBattleMap(BattleMapTokenGO battleMapToken, Graphics2D graphics) {
		ensure(battleMapToken != null, "battle map token cannot be null!");
		ensure(graphics != null, "graphics cannot be null!");
		CoordinatesXYGO leftUpperCorner = battleMapToken.getTokenLeftUpperCorner();
		int fieldSizeInPixels = battleMapToken.getBattleMap().getFieldSizeInPixels();
		BufferedImage token = battleMapToken.getToken().getBufferedImage();
		graphics.drawImage(token, leftUpperCorner.getX(), leftUpperCorner.getY(), null);
		if (battleMapToken instanceof DnDBattleMapTokenGO dndToken) {
			int x = leftUpperCorner.getX();
			int y = leftUpperCorner.getY();
			graphics.setColor(Color.LIGHT_GRAY);
			graphics.fillRect(
				x,
				y,
				(int) (fieldSizeInPixels * ((DnDTokenGO) dndToken.getToken()).getTokenSize().getFieldSize()),
				3
			);
			graphics.setColor(Color.BLACK);
			graphics.drawRect(
				x,
				y,
				(int) (fieldSizeInPixels * ((DnDTokenGO) dndToken.getToken()).getTokenSize().getFieldSize()),
				3
			);
		}
	}
}
