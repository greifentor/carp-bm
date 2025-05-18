package de.ollie.carp.bm.lib.server.rest.v1.dto;

import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class TokenDTO {

	private DndDataDTO dndData;
	private UUID id;
	private String name;
	private byte[] image;
	private RegelsystemDTO regelsystem;
}
