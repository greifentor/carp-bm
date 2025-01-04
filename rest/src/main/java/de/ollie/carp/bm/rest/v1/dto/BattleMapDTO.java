package de.ollie.carp.bm.rest.v1.dto;

import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class BattleMapDTO {

	private UUID id;
	private int fieldSize;
	private byte[] image;
	private String name;
}
