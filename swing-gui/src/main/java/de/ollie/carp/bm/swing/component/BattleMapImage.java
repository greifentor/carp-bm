package de.ollie.carp.bm.swing.component;

import de.ollie.carp.bm.client.v1.TokenClient;
import de.ollie.carp.bm.client.v1.dto.BattleMapTokenDTO;
import de.ollie.carp.bm.gui.TokenGUIService;
import de.ollie.carp.bm.gui.factory.ImageIconFactory;
import de.ollie.carp.bm.gui.go.BattleMapGO;
import de.ollie.carp.bm.gui.go.BattleMapTokenGO;
import de.ollie.carp.bm.gui.go.CoordinatesGO;
import de.ollie.carp.bm.gui.go.HitsGO;
import de.ollie.carp.bm.gui.mapper.BattleMapTokenGOMapper;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RequiredArgsConstructor
public class BattleMapImage extends JLabel implements MouseListener {

	public interface Listener {
		void hitsDetected(HitsGO hits);
	}

	private final Logger log = LogManager.getLogger(BattleMapImage.class);
	private final BattleMapGO battleMap;
	private final ImageIconFactory imageIconFactory;
	private final TokenClient tokenClient;
	private final TokenGUIService tokenGUIService;
	private final BattleMapTokenGOMapper battleMapTokenGOMapper;
	private final List<Listener> listeners = new ArrayList<>();

	public void update() {
		ImageIcon bmImage = imageIconFactory.create(battleMap.getImage());
		Image img = imageIconFactory.create(bmImage.getIconWidth(), bmImage.getIconHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.drawImage(bmImage.getImage(), 0, 0, null);
		getBattleMapTokenGOs().forEach(bmt -> tokenGUIService.setTokenToBattleMap(bmt, g));
		setIcon(new ImageIcon(img));
		removeMouseListener(this);
		addMouseListener(this);
		repaint();
	}

	private List<BattleMapTokenGO> getBattleMapTokenGOs() {
		List<BattleMapTokenDTO> l = tokenClient.findAllByBattleMap(battleMap.getName());
		l.forEach(System.out::println);
		battleMapTokenGOMapper.toGOs(l).forEach(System.out::println);
		return battleMapTokenGOMapper.toGOs(l);
	}

	public void addListener(Listener l) {
		listeners.add(l);
	}

	void fireEvent(int fieldX, int fieldY, List<BattleMapTokenGO> battleMapTokens) {
		HitsGO hits = new HitsGO().setBattleMapTokens(battleMapTokens).setFieldX(fieldX).setFieldY(fieldY);
		for (Listener l : listeners) {
			try {
				l.hitsDetected(hits);
			} catch (Exception e) {
				log.error("error while calling listener!", e);
			}
		}
	}

	public void removeListener(Listener l) {
		listeners.remove(l);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		CoordinatesGO coordinates = battleMap.getFieldCoordinates(e.getX(), e.getY());
		fireEvent(
			coordinates.getFieldX().intValue(),
			coordinates.getFieldY().intValue(),
			tokenGUIService.reduceToHitTokens(getBattleMapTokenGOs(), e.getX(), e.getY())
		);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// NOP
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// NOP
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// NOP
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// NOP
	}
}
