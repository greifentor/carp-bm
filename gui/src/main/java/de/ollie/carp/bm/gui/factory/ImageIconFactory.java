package de.ollie.carp.bm.gui.factory;

import java.awt.image.BufferedImage;

public interface ImageIconFactory {
	BufferedImage create(byte[] imageContent);
}
