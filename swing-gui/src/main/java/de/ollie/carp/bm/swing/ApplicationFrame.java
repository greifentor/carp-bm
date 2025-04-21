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
import de.ollie.carp.bm.swing.component.CarpBmMenuBar;
import de.ollie.carp.bm.swing.component.CarpBmMenuBar.MenuItemIdentifier;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named
public class ApplicationFrame
	extends JFrame
	implements WindowListener, BattleMapImage.Listener, CarpBmMenuBar.Observer {

	private static final Logger LOG = LogManager.getLogger(ApplicationFrame.class);

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

	private BattleMapGO battleMap;
	private BattleMapImage bmi;
	private JMenuBar menuBar;

	private boolean initialized = false;

	public ApplicationFrame() {
		super(";op");
	}

	@PostConstruct
	void postConstruct() {
		addWindowListener(this);
		setSize(new Dimension(800, 600));
		menuBar = new CarpBmMenuBar(this);
		setJMenuBar(menuBar);
		LOG.info("post constructed");
	}

	public void initialize() {
		battleMap =
			battleMapClient
				.findAllBattleMaps()
				.stream()
				.map(battleMapGOMapper::toGO)
				.findFirst()
				.orElseThrow(() -> new NoSuchElementException("Found no battle maps!"));
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
		bmi = new BattleMapImage(battleMap, imageIconFactory, tokenClient, tokenSetterService, battleMapTokenGOMapper);
		bmi.addListener(this);
		bmi.update();
		return bmi;
	}

	@Override
	public void windowActivated(WindowEvent e) {
		LOG.info("activated");
		// NOP
	}

	@Override
	public void windowClosed(WindowEvent e) {
		LOG.info("closed");
		// NOP
	}

	@Override
	public void windowClosing(WindowEvent e) {
		LOG.info("Closing");
		dispose();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		LOG.info("deactivated");
		// NOP
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		LOG.info("deiconified");
		// NOP
	}

	@Override
	public void windowIconified(WindowEvent e) {
		LOG.info("iconified");
		// NOP
	}

	@Override
	public void windowOpened(WindowEvent e) {
		LOG.info("opened");
	}

	private CoordinatesDTO selectedField;
	private BattleMapTokenGO selectedToken;

	@Override
	public void hitsDetected(HitsGO hits) {
		selectedToken =
			tokenClient.findSelectedTokenByBattleMap(battleMap.getName()).map(battleMapTokenGOMapper::toGO).orElse(null);
		selectedField =
			new CoordinatesDTO().setFieldX(new BigDecimal(hits.getFieldX())).setFieldY(new BigDecimal(hits.getFieldY()));
		if (!hits.getBattleMapTokens().isEmpty()) {
			LOG.info("Tokens hit: " + hits.getBattleMapTokens().size());
			if (selectedToken != null) {
				LOG.info("unselect " + selectedToken.getId().toString());
				tokenClient.unselectToken(selectedToken.getId().toString());
				selectedToken = null;
			} else {
				selectedToken = hits.getBattleMapTokens().get(0);
				tokenClient.selectToken(selectedToken.getId().toString());
				LOG.info("select " + selectedToken.getId().toString());
			}
		} else {
			if (selectedToken != null) {
				LOG.info("move " + selectedToken.getId().toString());
				tokenClient.moveBattleMapToken(selectedToken.getId().toString(), selectedField);
			}
		}
		LOG.info("selected token " + (selectedToken != null ? selectedToken.getId().toString() : "NONE"));
		bmi.update();
	}

	@Override
	public void menuItemSelected(MenuItemIdentifier selectedMenuItem) {
		System.out.println("\nMENU ITEM CLICKED: " + selectedMenuItem + "\n");
		if (selectedMenuItem == MenuItemIdentifier.FILE_QUIT) {
			System.exit(0);
		} else if (selectedMenuItem == MenuItemIdentifier.BATTLE_MAP_OPEN) {
			initialize();
		}
	}
}
