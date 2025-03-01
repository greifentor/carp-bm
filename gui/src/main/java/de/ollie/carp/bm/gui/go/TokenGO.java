package de.ollie.carp.bm.gui.go;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import javax.imageio.ImageIO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Generated;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@ToString(exclude = { "image" })
public class TokenGO {

	private UUID id;
	private byte[] image;
	private String name;
	private ShapeTypeGO shapeType;

	@Setter(AccessLevel.PRIVATE)
	private BufferedImage bufferedImage;

	public BufferedImage getBufferedImage() {
		if (bufferedImage == null) {
			try (InputStream inputStream = new ByteArrayInputStream(getImage())) {
				bufferedImage = ImageIO.read(inputStream);
			} catch (IOException e) {
				throw new IllegalStateException("error while creating buffered image for TokenGO: " + e.getMessage());
			}
		}
		return bufferedImage;
	}

	public TokenGO setImage(byte[] image) {
		this.image = image;
		this.bufferedImage = null;
		return this;
	}
}
