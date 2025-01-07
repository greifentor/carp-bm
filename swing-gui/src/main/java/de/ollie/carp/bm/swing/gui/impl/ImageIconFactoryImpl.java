package de.ollie.carp.bm.swing.gui.impl;

import de.ollie.carp.bm.gui.factory.ImageIconFactory;
import jakarta.inject.Named;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

@Named
public class ImageIconFactoryImpl implements ImageIconFactory {

	@Override
	public BufferedImage create(byte[] imageContent) {
		try (InputStream inputStream = new ByteArrayInputStream(imageContent)) {
			return ImageIO.read(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
