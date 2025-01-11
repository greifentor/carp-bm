package de.ollie.carp.bm.gui.factory;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public interface ImageIconFactory {
	BufferedImage create(byte[] imageContent);

	ImageIcon create(BufferedImage bufferedImage);

	BufferedImage create(int width, int height, int type);
}
