package de.ollie.carp.bm.swing;

import de.ollie.carp.bm.client.BattleMapClient;
import de.ollie.carp.bm.client.TokenClient;
import de.ollie.carp.bm.core.model.BattleMap;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@Named
public class ApplicationFrame extends JFrame implements WindowListener {

	private static final int HGAP = 3;
	private static final int VGAP = 3;

	@Inject
	private BattleMapClient battleMapClient;

	@Inject
	private TokenClient tokenClient;

	public ApplicationFrame() {
		super(";op");
	}

	@PostConstruct
	void postConstruct() {
		addWindowListener(this);
		setSize(new Dimension(800, 600));
		setContentPane(createMainPanel());
		setVisible(true);
		requestFocus();
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel(new BorderLayout(VGAP, HGAP));
		p.add(BorderLayout.CENTER, new JScrollPane(createImage()));
		return p;
	}

	private JLabel createImage() {
		BattleMap battleMap = battleMapClient.findAllBattleMaps().get(0);
		ImageIcon bmImage = new ImageIcon(battleMap.getImage());
		Image img = new BufferedImage(bmImage.getIconWidth(), bmImage.getIconHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.drawImage(bmImage.getImage(), 0, 0, null);
		tokenClient
			.findAllByBattleMap(battleMap.getName())
			.forEach(bmt ->
				System.out.println(
					bmt.getToken().getName() +
					" " +
					bmt.getToken().getImage().length +
					" " +
					bmt.getPositionX() +
					" " +
					bmt.getPositionY()
				)
			);
		tokenClient
			.findAllByBattleMap(battleMap.getName())
			.forEach(bmt -> {
				int x = (int) (50.0 * bmt.getPositionX()) + 12;
				int y = (int) (50.0 * bmt.getPositionY()) + 12;
				g.drawImage(new ImageIcon(bmt.getToken().getImage()).getImage(), x, y, null);
			});
		ImageIcon imageIcon = new ImageIcon(img);
		return new JLabel(imageIcon);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// NOP
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// NOP
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("Closed");
		dispose();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// NOP
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// NOP
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// NOP
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// NOP
	}
}
