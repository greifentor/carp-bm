package de.ollie.carp.bm.swing.component;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import de.ollie.carp.bm.swing.component.SimplifiedWindowListener.EventType;
import de.ollie.carp.bm.swing.component.SimplifiedWindowListener.Observer;
import java.awt.event.WindowEvent;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SimplifiedWindowListenerTest {

	@Mock
	private WindowEvent windowEvent;

	@Mock
	private Observer observer;

	@InjectMocks
	private SimplifiedWindowListener unitUnderTest;

	@Nested
	class windowActivated_WindowEvent {

		@Test
		void throwsAnException_passingANullValueAsEvent() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.windowActivated(null));
		}

		@Test
		void callsTheObserverCorrectly() {
			// Run
			unitUnderTest.windowActivated(windowEvent);
			// Check
			verify(observer, times(1)).windowEvent(EventType.ACTIVATED, windowEvent);
		}
	}

	@Nested
	class windowClosed_WindowEvent {

		@Test
		void throwsAnException_passingANullValueAsEvent() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.windowClosed(null));
		}

		@Test
		void callsTheObserverCorrectly() {
			// Run
			unitUnderTest.windowClosed(windowEvent);
			// Check
			verify(observer, times(1)).windowEvent(EventType.CLOSED, windowEvent);
		}
	}

	@Nested
	class windowClosing_WindowEvent {

		@Test
		void throwsAnException_passingANullValueAsEvent() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.windowClosing(null));
		}

		@Test
		void callsTheObserverCorrectly() {
			// Run
			unitUnderTest.windowClosing(windowEvent);
			// Check
			verify(observer, times(1)).windowEvent(EventType.CLOSING, windowEvent);
		}
	}

	@Nested
	class windowDeactivated_WindowEvent {

		@Test
		void throwsAnException_passingANullValueAsEvent() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.windowDeactivated(null));
		}

		@Test
		void callsTheObserverCorrectly() {
			// Run
			unitUnderTest.windowDeactivated(windowEvent);
			// Check
			verify(observer, times(1)).windowEvent(EventType.DEACTIVATED, windowEvent);
		}
	}

	@Nested
	class windowDeiconified_WindowEvent {

		@Test
		void throwsAnException_passingANullValueAsEvent() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.windowDeiconified(null));
		}

		@Test
		void callsTheObserverCorrectly() {
			// Run
			unitUnderTest.windowDeiconified(windowEvent);
			// Check
			verify(observer, times(1)).windowEvent(EventType.DEICONIFIED, windowEvent);
		}
	}

	@Nested
	class windowIconified_WindowEvent {

		@Test
		void throwsAnException_passingANullValueAsEvent() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.windowIconified(null));
		}

		@Test
		void callsTheObserverCorrectly() {
			// Run
			unitUnderTest.windowIconified(windowEvent);
			// Check
			verify(observer, times(1)).windowEvent(EventType.ICONIFIED, windowEvent);
		}
	}

	@Nested
	class windowOpened_WindowEvent {

		@Test
		void throwsAnException_passingANullValueAsEvent() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.windowOpened(null));
		}

		@Test
		void callsTheObserverCorrectly() {
			// Run
			unitUnderTest.windowOpened(windowEvent);
			// Check
			verify(observer, times(1)).windowEvent(EventType.OPENED, windowEvent);
		}
	}
}
