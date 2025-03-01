package de.ollie.carp.bm.swing;

import de.ollie.carp.bm.client.v1.BattleMapClient;
import de.ollie.carp.bm.client.v1.TokenClient;
import de.ollie.carp.bm.client.v1.dto.CoordinatesDTO;
import de.ollie.carp.bm.gui.TokenGUIService;
import de.ollie.carp.bm.gui.factory.ImageIconFactory;
import de.ollie.carp.bm.gui.go.BattleMapGO;
import de.ollie.carp.bm.gui.go.BattleMapTokenGO;
import de.ollie.carp.bm.gui.go.HitsGO;
import de.ollie.carp.bm.gui.mapper.BattleMapGOMapper;
import de.ollie.carp.bm.gui.mapper.BattleMapTokenGOMapper;
import de.ollie.carp.bm.swing.component.BattleMapImage;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigDecimal;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@Named
public class ApplicationFrame extends JFrame implements WindowListener, BattleMapImage.Listener {

	private static final int HGAP = 3;
	private static final int VGAP = 3;

	@Inject
	private ImageIconFactory imageIconFactory;

	@Inject
	private BattleMapGOMapper battleMapGOMapper;

	@Inject
	private BattleMapTokenGOMapper battleMapTokenGOMapper;

	@Inject
	private BattleMapClient battleMapClient;

	@Inject
	private TokenClient tokenClient;

	@Inject
	private TokenGUIService tokenSetterService;

	private BattleMapImage bmi;

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
		p.add(BorderLayout.NORTH, new JScrollPane(createImage()));
		return p;
	}

	private BattleMapImage createImage() {
		BattleMapGO battleMap = battleMapClient
			.findAllBattleMaps()
			.stream()
			.map(battleMapGOMapper::toGO)
			.findFirst()
			.orElse(null);
		bmi = new BattleMapImage(battleMap, imageIconFactory, tokenClient, tokenSetterService, battleMapTokenGOMapper);
		bmi.addListener(this);
		bmi.update();
		return bmi;
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

	private CoordinatesDTO selectedField;
	private BattleMapTokenGO selectedToken;

	@Override
	public void hitsDetected(HitsGO hits) {
		System.out.println("selected field was:    " + selectedField);
		System.out.println("selected token:        " + selectedToken);
		selectedField =
			new CoordinatesDTO().setFieldX(new BigDecimal(hits.getFieldX())).setFieldY(new BigDecimal(hits.getFieldY()));
		if ((selectedToken != null) && hits.getBattleMapTokens().isEmpty()) {
			tokenClient.moveBattleMapToken(selectedToken.getId().toString(), selectedField);
			bmi.update();
			System.out.println("repainted");
			selectedToken = null;
		} else if ((selectedToken == null) && !hits.getBattleMapTokens().isEmpty()) {
			selectedToken = hits.getBattleMapTokens().get(0);
		}
		System.out.println("selected field is now: " + selectedField);
		System.out.println("selected token:        " + selectedToken);
		System.out.println();
	}
}
