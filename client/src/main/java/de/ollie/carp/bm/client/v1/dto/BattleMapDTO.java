package de.ollie.carp.bm.client.v1.dto;

import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@ToString(exclude = { "image" })
public class BattleMapDTO {

	private UUID id;
	private int fieldSizeInPixels;
	private byte[] image;
	private String name;
	private int offsetInPixels;
}
