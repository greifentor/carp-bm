package de.ollie.carp.bm.swing.component;

import static de.ollie.carp.bm.util.Check.ensure;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CarpBmMenuBar extends JMenuBar {

	public enum MenuItemIdentifier {
		FILE_QUIT,
		BATTLE_MAP_OPEN,
	}

	public interface Observer {
		void menuItemSelected(MenuItemIdentifier selectedMenuItem);
	}

	private JMenu menuBattleMaps;
	private JMenu menuFile;
	private JMenuItem menuItemOpenBattleMap;
	private JMenuItem menuItemQuit;

	public CarpBmMenuBar(Observer observer) {
		super();
		ensure(observer != null, "observer cannot be null!");
		menuFile = new JMenu("Datei");
		menuItemQuit = new JMenuItem("Quit");
		menuItemQuit.addActionListener(e -> observer.menuItemSelected(MenuItemIdentifier.FILE_QUIT));
		menuFile.add(menuItemQuit);
		add(menuFile);
		menuBattleMaps = new JMenu("Battle Maps");
		menuItemOpenBattleMap = new JMenuItem("Open ...");
		menuItemOpenBattleMap.addActionListener(e -> observer.menuItemSelected(MenuItemIdentifier.BATTLE_MAP_OPEN));
		menuBattleMaps.add(menuItemOpenBattleMap);
		add(menuBattleMaps);
	}
}
