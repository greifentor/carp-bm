package de.ollie.carp.bm.swing.component;

import static de.ollie.carp.bm.util.Check.ensure;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimplifiedWindowListener implements WindowListener {

	private static final String MSG_E_CANNOT_BE_NULL = "e cannot be null!";

	public enum EventType {
		ACTIVATED,
		CLOSED,
		CLOSING,
		DEACTIVATED,
		DEICONIFIED,
		ICONIFIED,
		OPENED,
	}

	@NonNull
	private final Observer observer;

	public interface Observer {
		void windowEvent(EventType eventType, WindowEvent e);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		ensure(e != null, MSG_E_CANNOT_BE_NULL);
		observer.windowEvent(EventType.ACTIVATED, e);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		ensure(e != null, MSG_E_CANNOT_BE_NULL);
		observer.windowEvent(EventType.CLOSED, e);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		ensure(e != null, MSG_E_CANNOT_BE_NULL);
		observer.windowEvent(EventType.CLOSING, e);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		ensure(e != null, MSG_E_CANNOT_BE_NULL);
		observer.windowEvent(EventType.DEACTIVATED, e);
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		ensure(e != null, MSG_E_CANNOT_BE_NULL);
		observer.windowEvent(EventType.DEICONIFIED, e);
	}

	@Override
	public void windowIconified(WindowEvent e) {
		ensure(e != null, MSG_E_CANNOT_BE_NULL);
		observer.windowEvent(EventType.ICONIFIED, e);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		ensure(e != null, MSG_E_CANNOT_BE_NULL);
		observer.windowEvent(EventType.OPENED, e);
	}
}
