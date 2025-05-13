package de.ollie.carp.bm.swing;

import de.ollie.carp.bm.client.v1.BattleMapClient;
import de.ollie.carp.bm.client.v1.TokenClient;
import de.ollie.carp.bm.client.v1.dto.BattleMapTokenDataDTO;
import de.ollie.carp.bm.client.v1.dto.CoordinatesDTO;
import de.ollie.carp.bm.client.v1.dto.TokenDTO;
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
import de.ollie.carp.bm.swing.component.SimplifiedInternalFrameListener;
import de.ollie.carp.bm.swing.component.SimplifiedInternalFrameListener.EventType;
import de.ollie.carp.bm.swing.component.SimplifiedWindowListener;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.InternalFrameEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named
public class ApplicationFrame
	extends JFrame
	implements
		BattleMapImage.Listener,
		CarpBmMenuBar.Observer,
		SimplifiedInternalFrameListener.Observer,
		SimplifiedWindowListener.Observer {

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
	private JComboBox<TokenDTO> comboBoxIcons;
	private JMenuBar menuBar;
	private JDesktopPane desktopPane;

	private boolean initialized = false;

	public ApplicationFrame() {
		super(";op");
	}

	@PostConstruct
	void postConstruct() {
		addWindowListener(new SimplifiedWindowListener(this));
		setSize(new Dimension(800, 600));
		menuBar = new CarpBmMenuBar(this);
		setJMenuBar(menuBar);
		JLabel labelBackground = new JLabel(
			new ImageIcon("/home/ollie/rpg/DungeonsAndDragons/Material/Hintergrund-Einsteigerbox.jpg")
		);
		desktopPane = new JDesktopPane();
		desktopPane.setOpaque(false);
		labelBackground.setLayout(new BorderLayout());
		labelBackground.add(desktopPane, BorderLayout.CENTER);
		setLayout(new BorderLayout());
		setContentPane(labelBackground);
		setVisible(true);
		LOG.info("post constructed");
	}

	public void initialize() {
		JInternalFrame internalFrame = new JInternalFrame("Bla", true, true, true, true);
		internalFrame.addInternalFrameListener(new SimplifiedInternalFrameListener(this));
		desktopPane.add(internalFrame);
		battleMap =
			battleMapClient
				.findAllBattleMaps()
				.stream()
				.map(battleMapGOMapper::toGO)
				.findFirst()
				.orElseThrow(() -> new NoSuchElementException("Found no battle maps!"));
		internalFrame.setContentPane(createMainPanel());
		internalFrame.setVisible(true);
		internalFrame.setSize(600, 400);
		internalFrame.requestFocus();
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel(new BorderLayout(VGAP, HGAP));
		JPanel p0 = new JPanel(new BorderLayout(VGAP, HGAP));
		comboBoxIcons = new JComboBox<>();
		comboBoxIcons.setModel(new DefaultComboBoxModel<>(tokenClient.findAllTokens().toArray(new TokenDTO[0])));
		p0.add(BorderLayout.NORTH, comboBoxIcons);
		p0.add(BorderLayout.CENTER, new JScrollPane(createImage()));
		p.add(BorderLayout.NORTH, p0);
		return p;
	}

	private BattleMapImage createImage() {
		bmi = new BattleMapImage(battleMap, imageIconFactory, tokenClient, tokenSetterService, battleMapTokenGOMapper);
		bmi.addListener(this);
		bmi.update();
		return bmi;
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
			LOG.info("Tokens hit: {}", hits.getBattleMapTokens().size());
			if (selectedToken != null) {
				LOG.info("unselect {}", selectedToken.getId());
				tokenClient.unselectToken(selectedToken.getId().toString());
				selectedToken = null;
			} else {
				selectedToken = hits.getBattleMapTokens().get(0);
				tokenClient.selectToken(selectedToken.getId().toString());
				LOG.info("select {}", selectedToken.getId());
			}
		} else {
			if (selectedToken != null) {
				LOG.info("move {}", selectedToken.getId());
				tokenClient.moveBattleMapToken(selectedToken.getId().toString(), selectedField);
			} else if (comboBoxIcons.getSelectedItem() != null) {
				TokenDTO tokenDTO = ((TokenDTO) comboBoxIcons.getSelectedItem());
				LOG.info("add {}", tokenDTO.getId());
				tokenClient.setTokenToBattleMapOfSpielrunde(
					tokenDTO.getId().toString(),
					battleMap.getId().toString(),
					new BattleMapTokenDataDTO()
						.setCoordinates(
							new CoordinatesDTO().setFieldX(selectedField.getFieldX()).setFieldY(selectedField.getFieldY())
						)
				);
			}
		}
		LOG.info("selected token {}", (selectedToken != null ? selectedToken.getId().toString() : "NONE"));
		bmi.update();
	}

	@Override
	public void menuItemSelected(MenuItemIdentifier selectedMenuItem) {
		if (selectedMenuItem == MenuItemIdentifier.FILE_QUIT) {
			System.exit(0);
		} else if (selectedMenuItem == MenuItemIdentifier.BATTLE_MAP_OPEN) {
			initialize();
		}
	}

	@Override
	public void internalFrameEvent(EventType eventType, InternalFrameEvent event) {
		if (eventType == EventType.CLOSED) {
			desktopPane.remove(event.getInternalFrame());
		}
	}

	@Override
	public void windowEvent(SimplifiedWindowListener.EventType eventType, WindowEvent e) {
		if (SimplifiedWindowListener.EventType.CLOSING == eventType) {
			dispose();
			System.exit(0);
		}
	}
}
