package de.ollie.carp.bm.swing;

import de.ollie.carp.bm.client.BattleMapClient;
import de.ollie.carp.bm.client.TokenClient;
import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.gui.TokenSetterService;
import de.ollie.carp.bm.gui.factory.ImageIconFactory;
import de.ollie.carp.bm.swing.component.BattleMapImage;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@Named
public class ApplicationFrame extends JFrame implements WindowListener {

	private static final int HGAP = 3;
	private static final int VGAP = 3;

	@Inject
	private ImageIconFactory imageIconFactory;

	@Inject
	private BattleMapClient battleMapClient;

	@Inject
	private TokenClient tokenClient;

	@Inject
	private TokenSetterService tokenSetterService;

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

	private BattleMapImage createImage() {
		BattleMap battleMap = battleMapClient.findAllBattleMaps().get(0);
		return new BattleMapImage(
			battleMap,
			tokenClient.findAllByBattleMap(battleMap.getName()),
			tokenSetterService,
			imageIconFactory
		);
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
