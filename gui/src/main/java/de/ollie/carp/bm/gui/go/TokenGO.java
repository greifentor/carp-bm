package de.ollie.carp.bm.gui.go;

import java.awt.image.BufferedImage;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@ToString(exclude = { "image" })
public class TokenGO {

	private UUID id;
	private BufferedImage image;
	private String name;
}
