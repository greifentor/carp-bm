package de.ollie.carp.bm.gui.factory;

import de.ollie.carp.bm.gui.go.CoordinatesXYGO;
import java.awt.Shape;
import java.awt.image.BufferedImage;

public interface ShapeFactory {
	Shape create(CoordinatesXYGO leftUpperCorner, BufferedImage token);
}
