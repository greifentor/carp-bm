package de.ollie.carp.bm.swing.component;

import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.gui.TokenSetterService;
import de.ollie.carp.bm.gui.factory.ImageIconFactory;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BattleMapImage extends JLabel {

	public BattleMapImage(
		BattleMap battleMap,
		List<BattleMapToken> battleMapTokens,
		TokenSetterService tokenSetterService,
		ImageIconFactory imageIconFactory
	) {
		ImageIcon bmImage = new ImageIcon(battleMap.getImage());
		Image img = new BufferedImage(bmImage.getIconWidth(), bmImage.getIconHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.drawImage(bmImage.getImage(), 0, 0, null);
		battleMapTokens.forEach(bmt -> tokenSetterService.setTokenToBattleMap(bmt, g));
		setIcon(new ImageIcon(img));
		addMouseListener(
			new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					System.out.println("Mouse clicked at " + e.getX() + "/" + e.getY());
					battleMapTokens
						.stream()
						.filter(bmt -> isHit(bmt, e.getX(), e.getY(), imageIconFactory))
						.forEach(System.out::println);
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}
			}
		);
	}

	private boolean isHit(BattleMapToken bmt, int x, int y, ImageIconFactory imageIconFactory) {
		BattleMap bm = bmt.getBattleMap();
		BufferedImage token = imageIconFactory.create(bmt.getToken().getImage());
		int posX = (int) (bmt.getFieldX().doubleValue() * bm.getFieldSizeInPixels()) + bm.getOffsetInPixels();
		int posY = (int) (bmt.getFieldY().doubleValue() * bm.getFieldSizeInPixels()) + bm.getOffsetInPixels();
		Shape s = new Rectangle2D.Double(posX, posY, token.getWidth(null), token.getHeight(null));
		return s.contains(x, y) && !transparent(token, x - posX, y - posY);
	}

	private boolean transparent(BufferedImage image, int x, int y) {
		return image.getRGB(x, y) == 0;
	}
}
