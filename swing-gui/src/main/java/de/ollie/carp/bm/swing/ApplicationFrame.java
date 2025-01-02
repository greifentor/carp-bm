package de.ollie.carp.bm.swing;

import jakarta.inject.Named;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

@Named
public class ApplicationFrame extends JFrame implements WindowListener {

	public ApplicationFrame() {
		super(";op");
		addWindowListener(this);
		setSize(new Dimension(300, 100));
		// pack();
		setVisible(true);
		requestFocus();
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// NOP
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("Closed");
		// NOP
	}

	@Override
	public void windowClosing(WindowEvent e) {}

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
