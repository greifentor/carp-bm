package de.ollie.carp.bm.swing.component;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import de.ollie.carp.bm.swing.component.SimplifiedInternalFrameListener.EventType;
import de.ollie.carp.bm.swing.component.SimplifiedInternalFrameListener.Observer;
import javax.swing.event.InternalFrameEvent;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SimplifiedInternalFrameListenerTest {

	@Mock
	private InternalFrameEvent internalFrameEvent;

	@Mock
	private Observer observer;

	@InjectMocks
	private SimplifiedInternalFrameListener unitUnderTest;

	@Nested
	class internalFrameActivated_InternalFrameEvent {

		@Test
		void throwsAnException_passingANullValueAsEvent() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.internalFrameActivated(null));
		}

		@Test
		void callsTheObserverCorrectly() {
			// Run
			unitUnderTest.internalFrameActivated(internalFrameEvent);
			// Check
			verify(observer, times(1)).internalFrameEvent(EventType.ACTIVATED, internalFrameEvent);
		}
	}

	@Nested
	class internalFrameClosed_InternalFrameEvent {

		@Test
		void throwsAnException_passingANullValueAsEvent() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.internalFrameClosed(null));
		}

		@Test
		void callsTheObserverCorrectly() {
			// Run
			unitUnderTest.internalFrameClosed(internalFrameEvent);
			// Check
			verify(observer, times(1)).internalFrameEvent(EventType.CLOSED, internalFrameEvent);
		}
	}

	@Nested
	class internalFrameClosing_InternalFrameEvent {

		@Test
		void throwsAnException_passingANullValueAsEvent() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.internalFrameClosing(null));
		}

		@Test
		void callsTheObserverCorrectly() {
			// Run
			unitUnderTest.internalFrameClosing(internalFrameEvent);
			// Check
			verify(observer, times(1)).internalFrameEvent(EventType.CLOSING, internalFrameEvent);
		}
	}

	@Nested
	class internalFrameDeactivated_InternalFrameEvent {

		@Test
		void throwsAnException_passingANullValueAsEvent() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.internalFrameDeactivated(null));
		}

		@Test
		void callsTheObserverCorrectly() {
			// Run
			unitUnderTest.internalFrameDeactivated(internalFrameEvent);
			// Check
			verify(observer, times(1)).internalFrameEvent(EventType.DEACTIVATED, internalFrameEvent);
		}
	}

	@Nested
	class internalFrameDeiconified_InternalFrameEvent {

		@Test
		void throwsAnException_passingANullValueAsEvent() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.internalFrameDeiconified(null));
		}

		@Test
		void callsTheObserverCorrectly() {
			// Run
			unitUnderTest.internalFrameDeiconified(internalFrameEvent);
			// Check
			verify(observer, times(1)).internalFrameEvent(EventType.DEICONIFIED, internalFrameEvent);
		}
	}

	@Nested
	class internalFrameIconified_InternalFrameEvent {

		@Test
		void throwsAnException_passingANullValueAsEvent() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.internalFrameIconified(null));
		}

		@Test
		void callsTheObserverCorrectly() {
			// Run
			unitUnderTest.internalFrameIconified(internalFrameEvent);
			// Check
			verify(observer, times(1)).internalFrameEvent(EventType.ICONIFIED, internalFrameEvent);
		}
	}

	@Nested
	class internalFrameOpened_InternalFrameEvent {

		@Test
		void throwsAnException_passingANullValueAsEvent() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.internalFrameOpened(null));
		}

		@Test
		void callsTheObserverCorrectly() {
			// Run
			unitUnderTest.internalFrameOpened(internalFrameEvent);
			// Check
			verify(observer, times(1)).internalFrameEvent(EventType.OPENED, internalFrameEvent);
		}
	}
}
