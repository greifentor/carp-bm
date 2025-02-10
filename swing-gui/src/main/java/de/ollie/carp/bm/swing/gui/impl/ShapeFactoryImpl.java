package de.ollie.carp.bm.swing.gui.impl;

import static de.ollie.carp.bm.util.Check.ensure;

import de.ollie.carp.bm.gui.factory.ShapeFactory;
import de.ollie.carp.bm.gui.go.CoordinatesXYGO;
import jakarta.inject.Named;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

@Named
public class ShapeFactoryImpl implements ShapeFactory {

	@Override
	public Shape create(CoordinatesXYGO leftUpperCorner, BufferedImage token) {
		ensure(leftUpperCorner != null, "coordinates for left upper corner cannot be null!");
		ensure(token != null, "token cannot be null!");
		return new Rectangle2D.Double(leftUpperCorner.getX(), leftUpperCorner.getY(), token.getWidth(), token.getHeight());
	}
}
