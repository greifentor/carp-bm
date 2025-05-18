package de.ollie.carp.bm.lib.server.persistence.entity;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class DnDDataDBO {

	private int initiativeBonus;
	private int rk;
	private DnDTokenSizeDBO tokenSize;
	private int tpMaximum;
}
