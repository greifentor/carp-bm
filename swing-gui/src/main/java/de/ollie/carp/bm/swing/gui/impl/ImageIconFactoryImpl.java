package de.ollie.carp.bm.swing.gui.impl;

import static de.ollie.carp.bm.util.Check.ensure;

import de.ollie.carp.bm.gui.factory.ImageIconFactory;
import jakarta.inject.Named;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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

	@Override
	public ImageIcon create(BufferedImage bufferedImage) {
		ensure(bufferedImage != null, "buffered image cannot be null.");
		return new ImageIcon(bufferedImage);
	}

	@Override
	public BufferedImage create(int width, int height, int type) {
		return new BufferedImage(width, height, type);
	}
}
