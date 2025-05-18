package de.ollie.carp.bm.lib.server.core.model;

import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class Token {

	private DnDData dndData;
	private UUID id;
	private String name;
	private byte[] image;
	private Regelsystem regelsystem;
}
