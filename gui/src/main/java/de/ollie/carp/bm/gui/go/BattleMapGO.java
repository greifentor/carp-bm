package de.ollie.carp.bm.gui.go;

import de.ollie.carp.bm.core.model.Coordinates;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@ToString(exclude = { "image" })
public class BattleMapGO {

	private UUID id;
	private int fieldSizeInPixels;
	private BufferedImage image;
	private String name;
	private int offsetInPixels;

	public Coordinates getFieldCoordinates(int x, int y) {
		String fieldX = ((x - offsetInPixels) / fieldSizeInPixels) + ".0";
		String fieldY = ((y - offsetInPixels) / fieldSizeInPixels) + ".0";
		return new Coordinates().setFieldX(new BigDecimal(fieldX)).setFieldY(new BigDecimal(fieldY));
	}
}
