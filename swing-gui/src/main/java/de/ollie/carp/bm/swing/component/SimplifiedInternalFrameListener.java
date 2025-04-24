package de.ollie.carp.bm.swing.component;

import static de.ollie.carp.bm.util.Check.ensure;

import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimplifiedInternalFrameListener implements InternalFrameListener {

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

	public interface Observer {
		void internalFrameEvent(EventType eventType, InternalFrameEvent e);
	}

	@NonNull
	private final Observer observer;

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		ensure(e != null, MSG_E_CANNOT_BE_NULL);
		observer.internalFrameEvent(EventType.ACTIVATED, e);
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		ensure(e != null, MSG_E_CANNOT_BE_NULL);
		observer.internalFrameEvent(EventType.CLOSED, e);
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		ensure(e != null, MSG_E_CANNOT_BE_NULL);
		observer.internalFrameEvent(EventType.CLOSING, e);
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		ensure(e != null, MSG_E_CANNOT_BE_NULL);
		observer.internalFrameEvent(EventType.DEACTIVATED, e);
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		ensure(e != null, MSG_E_CANNOT_BE_NULL);
		observer.internalFrameEvent(EventType.DEICONIFIED, e);
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		ensure(e != null, MSG_E_CANNOT_BE_NULL);
		observer.internalFrameEvent(EventType.ICONIFIED, e);
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		ensure(e != null, MSG_E_CANNOT_BE_NULL);
		observer.internalFrameEvent(EventType.OPENED, e);
	}
}
