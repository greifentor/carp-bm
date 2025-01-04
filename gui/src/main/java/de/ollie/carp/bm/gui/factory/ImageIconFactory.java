package de.ollie.carp.bm.gui.factory;

import jakarta.inject.Named;
import javax.swing.ImageIcon;

@Named
public class ImageIconFactory {

	public ImageIcon create(byte[] imageContent) {
		return new ImageIcon(imageContent);
	}
}
