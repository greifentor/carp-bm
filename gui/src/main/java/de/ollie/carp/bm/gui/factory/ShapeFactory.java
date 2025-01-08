package de.ollie.carp.bm.gui.factory;

import de.ollie.carp.bm.core.model.CoordinatesXY;
import java.awt.Shape;
import java.awt.image.BufferedImage;

public interface ShapeFactory {
	Shape create(CoordinatesXY leftUpperCorner, BufferedImage token);
}
