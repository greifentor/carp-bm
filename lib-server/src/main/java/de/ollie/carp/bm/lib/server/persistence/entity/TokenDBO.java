package de.ollie.carp.bm.lib.server.persistence.entity;

import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class TokenDBO {

	private DnDDataDBO dndData;
	private UUID id;
	private String name;
	private byte[] image;
	private RegelsystemDBO regelsystem;
}
