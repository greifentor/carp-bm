package de.ollie.carp.bm.lib.server.core.model;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class DnDData {

	private int initiativeBonus;
	private int rk;
	private DnDTokenSize tokenSize;
	private int tpMaximum;
}
