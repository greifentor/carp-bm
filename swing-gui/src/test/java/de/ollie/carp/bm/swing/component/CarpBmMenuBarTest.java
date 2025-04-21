package de.ollie.carp.bm.swing.component;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import de.ollie.carp.bm.swing.component.CarpBmMenuBar.MenuItemIdentifier;
import de.ollie.carp.bm.swing.component.CarpBmMenuBar.Observer;
import javax.swing.JMenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class CarpBmMenuBarTest {

	@Mock
	private Observer observer;

	private CarpBmMenuBar unitUnderTest;

	@BeforeEach
	void beforeEach() {
		unitUnderTest = new CarpBmMenuBar(observer);
	}

	@Nested
	class constructor_Observer {

		@Test
		void throwsAnException_passingANullValue_asObserver() {
			assertThrows(IllegalArgumentException.class, () -> new CarpBmMenuBar(null));
		}
	}

	@Nested
	class MenuClicks {

		@Test
		void obsererReturnsCorrectIdentifier_whenBattleMapOpenedMenuItemClicked() {
			// Prepare
			JMenuItem menuItem = (JMenuItem) ReflectionTestUtils.getField(unitUnderTest, "menuItemOpenBattleMap");
			// Run
			menuItem.doClick();
			// Check
			verify(observer, times(1)).menuItemSelected(MenuItemIdentifier.BATTLE_MAP_OPEN);
		}
	}
}
