package de.ollie.carp.bm.swing.component;

import de.ollie.carp.bm.gui.TokenGUIService;
import de.ollie.carp.bm.gui.factory.ImageIconFactory;
import de.ollie.carp.bm.gui.go.BattleMapGO;
import de.ollie.carp.bm.gui.go.BattleMapTokenGO;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BattleMapImage extends JLabel {

	public BattleMapImage(
		BattleMapGO battleMap,
		List<BattleMapTokenGO> battleMapTokens,
		TokenGUIService tokenGUIService,
		ImageIconFactory imageIconFactory
	) {
		ImageIcon bmImage = new ImageIcon(battleMap.getImage());
		Image img = new BufferedImage(bmImage.getIconWidth(), bmImage.getIconHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.drawImage(bmImage.getImage(), 0, 0, null);
		battleMapTokens.forEach(bmt -> tokenGUIService.setTokenToBattleMap(bmt, g));
		setIcon(new ImageIcon(img));
		addMouseListener(
			new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					System.out.println("Mouse clicked at " + e.getX() + "/" + e.getY());
					battleMapTokens
						.stream()
						.filter(bmt -> tokenGUIService.isHit(bmt, e.getX(), e.getY()))
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
}
